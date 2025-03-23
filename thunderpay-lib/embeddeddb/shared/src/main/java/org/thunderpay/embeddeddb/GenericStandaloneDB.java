/**
 * @file GenericStandaloneDB.java
 * @author Krisna Pranav
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.embeddeddb;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicBoolean;

public class GenericStandaloneDB extends EmbeddedDB {

    protected final AtomicBoolean started = new AtomicBoolean(false);

    public GenericStandaloneDB(final String databaseName, final String username, final String password, final String jdbcConnectionString) {
        super(databaseName, username, password, jdbcConnectionString);
    }

    @Override
    public DBEngine getDBEngine() {
        return DBEngine.GENERIC;
    }

    @Override
    public void initialize() throws IOException, SQLException {
    }

    @Override
    public void start() throws IOException {
        started.set(true);
        refreshTableNames();
    }

    @Override
    public void refreshTableNames() throws IOException {
    }

    @Override
    public void stop() throws IOException {
        started.set(false);
        super.stop();
    }
}