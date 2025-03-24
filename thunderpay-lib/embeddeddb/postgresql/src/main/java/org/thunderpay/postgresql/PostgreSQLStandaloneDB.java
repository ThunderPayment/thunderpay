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
import org.thunderpay.embeddeddb.GenericStandaloneDB;

public class PostgreSQLStandaloneDB extends GenericStandaloneDB {
    private final int port;

    public PostgreSQLStandaloneDB(final String databaseName, final String username, final String password) {
        this(databaseName, username, password, "jdbc:postgresql://localhost:5432/" + databaseName);
    }
}
