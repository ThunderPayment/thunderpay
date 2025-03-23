/**
 * @file MySQLStandaloneDB.java
 * @author Krisna Pranav
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.mysql;

import java.io.IOException;
import java.net.URI;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.thunderpay.embeddeddb.GenericStandaloneDB;
import com.mysql.cj.jdbc.MysqlDataSource;


public class MySQLStandaloneDB extends GenericStandaloneDB {

    private final int port;
    private final boolean useMariaDB;

    public MySQLStandaloneDB(final String databaseName) {
        this(databaseName, "root", null);
    }

    public MySQLStandaloneDB(final String databaseName, final String username, final String password) {
        this(databaseName, username, password, "jdbc:mysql://localhost:3306/" + databaseName + "?createDatabaseIfNotExist=true&allowMultiQueries=true");
    }

    public MySQLStandaloneDB(final String databaseName, final String username, final String password, final String jdbcConnectionString) {
        this(databaseName, username, password, jdbcConnectionString, true);
    }

    public MySQLStandaloneDB(final String databaseName, final String username, final String password, final String jdbcConnectionString, final boolean useMariaDB) {
        super(databaseName, username, password, jdbcConnectionString);
        this.port = URI.create(jdbcConnectionString.substring(5)).getPort();
        this.useMariaDB = useMariaDB;
    }

    @Override
    public DBEngine getDBEngine() {
        return DBEngine.MYSQL;
    }

    @Override
    public void initialize() throws IOException, SQLException {
        super.initialize();

        if (useMariaDB) {
            final ThunderpayMariaDbDataSource mariaDBDataSource = new ThunderpayMariaDbDataSource();
            try {
                mariaDBDataSource.setUrl(jdbcConnectionString);
            } catch (final SQLException e) {
                throw new IOException(e);
            }
            mariaDBDataSource.setUser(username);
            mariaDBDataSource.setPassword(password);
            dataSource = mariaDBDataSource;
        } else {
            final MysqlDataSource mysqlDataSource = new MysqlDataSource();
            mysqlDataSource.setDatabaseName(databaseName);
            mysqlDataSource.setUser(username);
            mysqlDataSource.setPassword(password);
            mysqlDataSource.setPort(port);

            mysqlDataSource.setURL(jdbcConnectionString);
            dataSource = mysqlDataSource;
        }
    }

    @Override
    public void refreshTableNames() throws IOException {
        final String query = String.format("select table_name from information_schema.tables where table_schema = '%s' and table_type = 'BASE TABLE';", databaseName);
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
        return String.format("mysql -u%s -p%s -P%s %s", username, password, port, databaseName);
    }
}