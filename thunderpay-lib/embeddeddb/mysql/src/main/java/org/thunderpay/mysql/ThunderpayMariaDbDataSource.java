/**
 * @file ThunderpayMariaDbDataSource.java
 * @author Krisna Pranav
 * @version 1.0
 * @date 2025-03-23
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.mysql;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.TreeMap;

import org.thunderpay.commons.utils.MapJoiner;
import org.thunderpay.commons.utils.annotation.VisibleForTesting;
import org.mariadb.jdbc.Configuration;
import org.mariadb.jdbc.MariaDbDataSource;

public class ThunderpayMariaDbDataSource extends MariaDbDataSource {
    private static final MapJoiner mapJoiner = new MapJoiner("=", "&");

    private String url;

    private Boolean cachePrepStmts;
    private Boolean useServerPrepStmts;

    private Integer prepStmtCacheSize;
    private Integer prepStmtCacheSqlLimit;

    @Override
    public void setUrl(final String url) throws IOException {
        this.url = url;
        updateUrlIfNedded();
    }

    private void updateUrlIfNedded() throws SQLException {
        if (url != null) {
            this.url = buildUpdatedUrl(this.url);
            super.setUrl(url);
        }
    }

    @VisibleForTesting
    String buildUpdatedUrl(final String url) throws SQLException {
        final Properties propertyWithPermitMysqlScheme = new Properties();
    }
}