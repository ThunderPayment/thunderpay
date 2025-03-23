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
    private Integer prepStmtCacheSize;
    private Integer prepStmtCacheSqlLimit;
    private Boolean useServerPrepStmts;

    @Override
    public void setUrl(final String url) throws SQLException {
        this.url = url;
        updateUrlIfNeeded(); 
    }

    public void setCachePrepStmts(final boolean cachePrepStmts) throws SQLException {
        this.cachePrepStmts = cachePrepStmts;
        updateUrlIfNeeded();
    }

    public void setPrepStmtCacheSize(final int prepStmtCacheSize) throws SQLException {
        this.prepStmtCacheSize = prepStmtCacheSize;
        updateUrlIfNeeded();
    }

    public void setPrepStmtCacheSqlLimit(final int prepStmtCacheSqlLimit) throws SQLException {
        this.prepStmtCacheSqlLimit = prepStmtCacheSqlLimit;
        updateUrlIfNeeded();
    }

    public void setUseServerPrepStmts(final boolean useServerPrepStmts) throws SQLException {
        this.useServerPrepStmts = useServerPrepStmts;
        updateUrlIfNeeded();
    }

    private void updateUrlIfNeeded() throws SQLException {
        if (url != null) {
            this.url = buildUpdatedUrl(this.url);
            super.setUrl(url);
        }
    }

    @VisibleForTesting
    String buildUpdatedUrl(final String url) throws SQLException {
        final Properties propertyWithPermitMysqlScheme = new Properties();
        propertyWithPermitMysqlScheme.put("permitMysqlScheme", "true");
        final String baseUrlWithPermitMysqlScheme = buildUpdatedUrl(url, propertyWithPermitMysqlScheme, true);

        final Properties props = new Properties();
        Configuration.parse(baseUrlWithPermitMysqlScheme, props);

        if (cachePrepStmts != null && !props.containsKey("cachePrepStmts")) {
            props.put("cachePrepStmts", cachePrepStmts);
        }

        if (prepStmtCacheSize != null && !props.containsKey("prepStmtCacheSize")) {
            props.put("prepStmtCacheSize", prepStmtCacheSize);
        }

        if (prepStmtCacheSqlLimit != null && !props.containsKey("prepStmtCacheSqlLimit")) {
            props.put("prepStmtCacheSqlLimit", prepStmtCacheSqlLimit);
        }

        if (useServerPrepStmts != null && !props.containsKey("useServerPrepStmts")) {
            props.put("useServerPrepStmts", useServerPrepStmts);
        }

        return buildUpdatedUrl(url, props, false);
    }

    private String buildUpdatedUrl(final String url, final Properties props, final boolean append) {
        if (props.isEmpty()) {
            return url;
        }

        final int separator = url.indexOf("//");
        final String urlSecondPart = url.substring(separator + 2);
        final int paramIndex = urlSecondPart.indexOf("?");

        if (append) {
            return url + (paramIndex > 0 ? "&" : "?") + mapJoiner.join(new TreeMap<>(props));
        } else {
            final String baseUrl = paramIndex > 0 ? url.substring(0, separator + 2 + paramIndex) : url;
            return baseUrl + "?" + mapJoiner.join(new TreeMap<>(props));
        }
    }
}