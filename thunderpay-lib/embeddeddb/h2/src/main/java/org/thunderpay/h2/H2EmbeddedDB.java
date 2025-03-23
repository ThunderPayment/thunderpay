/**
 * @file H2EmbeddedDB
 * @author Krisna Pranav
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.embeddeddb;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.sql.DataSource;
import org.h2.api.ErrorCode;
import org.h2.jdbcx.JdbcConnectionPool;
import org.h2.tools.Server;
import org.thunderpay.commons.embeddeddb.EmbeddedDB;

public class H2EmbeddedDB extends EmbeddedDB {

    private final AtomicBoolean started = new AtomicBoolean(false);

    private Server server;

    static {
        try {
            Class.forName("org.h2.Driver");
        } catch (final ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public H2EmbeddedDB() {
        this(UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString());
    }

    public H2EmbeddedDB(final String databaseName, final String username, final String password) {
        // Use LEGACY compatibility mode to support the SERIAL data type and to allow comparison between numeric and boolean values
        this(databaseName, username, password, "jdbc:h2:mem:" + databaseName + ";MODE=LEGACY;DB_CLOSE_DELAY=-1");
    }

    public H2EmbeddedDB(final String databaseName, final String username, final String password, final String jdbcConnectionString) {
        super(databaseName, username, password, jdbcConnectionString);
    }

    @Override
    public DBEngine getDBEngine() {
        return DBEngine.H2;
    }

    @Override
    public void initialize() throws IOException {
    }

    @Override
    public void start() throws IOException {
        if (started.get()) {
            throw new IOException("H2 is already running: " + jdbcConnectionString);
        }
        createDataSource();

        try {
            // Start a web server for debugging (http://127.0.0.1:8082/)
            server = Server.createWebServer().start();
            logger.info(String.format("H2 started on http://127.0.0.1:8082. JDBC=%s, Username=%s, Password=%s",
                    getJdbcConnectionString(), getUsername(), getPassword()));
        } catch (final SQLException e) {
            // H2 most likely already started (e.g. by a different pool) -- ignore
            // Note: we still want the EmbeddedDB object to be started, for a clean shutdown of the dataSource
            if (!String.valueOf(ErrorCode.EXCEPTION_OPENING_PORT_2).equals(e.getSQLState())) {
                throw new IOException(e);
            }

        }

        started.set(true);

        refreshTableNames();
    }

    @Override
    public void refreshTableNames() throws IOException {
        final String query = "select table_name from information_schema.tables where table_schema = current_schema() and table_type = 'BASE TABLE';";
        try {
            executeQuery(query, new ResultSetJob() {
                @Override
                public void work(final ResultSet resultSet) throws SQLException {
                    allTables.clear();
                    while (resultSet.next()) {
                        allTables.add(resultSet.getString(1));
                    }
                }
            });
        } catch (final SQLException e) {
            throw new IOException(e);
        }
    }

    protected void createDataSource() throws IOException {
        if (useConnectionPooling()) {
            dataSource = createHikariDataSource();
        } else {
            final JdbcConnectionPool jdbcConnectionPool = JdbcConnectionPool.create(jdbcConnectionString, username, password);

            jdbcConnectionPool.setMaxConnections(100);
            dataSource = jdbcConnectionPool;
        }
    }

    @Override
    public DataSource getDataSource() throws IOException {
        if (!started.get()) {
            throw new IOException("H2 is not running");
        }
        return super.getDataSource();
    }

    @Override
    public void stop() throws IOException {
        if (!started.get()) {
            throw new IOException("H2 is not running");
        }

        super.stop();

        try (final Connection connection = DriverManager.getConnection(jdbcConnectionString, username, password);
             final Statement statement = connection.createStatement()) {
            statement.execute("SHUTDOWN");
        } catch (final Exception e) {
            logger.warn("Exception while trying to shutdown H2", e);
        }

        if (dataSource instanceof JdbcConnectionPool) {
            ((JdbcConnectionPool) dataSource).dispose();
        }

        if (server != null) {
            server.stop();
            server = null;
        }
        started.set(false);
        logger.info(String.format("H2 stopped on http://127.0.0.1:8082. JDBC=%s, Username=%s, Password=%s",
                getJdbcConnectionString(), getUsername(), getPassword()));

    }

    @Override
    public String getCmdLineConnectionString() {
        return "open " + server.getURL();
    }
}