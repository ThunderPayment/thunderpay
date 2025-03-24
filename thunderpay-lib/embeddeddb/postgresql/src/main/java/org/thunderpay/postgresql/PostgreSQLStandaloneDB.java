/**
 * @file PostgreSQLStandaloneDB.java
 * @author Krisna Pranav
 * @version 1.0
 * @date 2025-03-24
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.postgresql;

import java.io.IOException;
import java.net.URI;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.postgresql.ds.PGSimpleDataSource;
import org.thunderpay.embeddeddb.GenericStandaloneDB;

public class PostgreSQLStandaloneDB extends GenericStandaloneDB {

    private final int port;

    public PostgreSQLStandaloneDB(final String databaseName, final String username, final String password) {
        this(databaseName, username, password, "jdbc:postgresql://localhost:5432/" + databaseName);
    }

    public PostgreSQLStandaloneDB(final String databaseName, final String username, final String password, final String jdbcConnectionString) {
        super(databaseName, username, password, jdbcConnectionString);
        this.port = URI.create(jdbcConnectionString.substring(5)).getPort();
    }

    @Override
    public DBEngine getDBEngine() {
        return DBEngine.POSTGRESQL;
    }

    @Override
    public void initialize() throws IOException, SQLException {
        super.initialize();
        dataSource = new PGSimpleDataSource();
        ((PGSimpleDataSource) dataSource).setDatabaseName(databaseName);
        ((PGSimpleDataSource) dataSource).setUser(username);
        ((PGSimpleDataSource) dataSource).setPassword(password);
        ((PGSimpleDataSource) dataSource).setUrl(jdbcConnectionString);
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

    @Override
    public String getCmdLineConnectionString() {
        return String.format("PGPASSWORD=%s psql -U%s -p%s %s", password, username, port, databaseName);
    }
}