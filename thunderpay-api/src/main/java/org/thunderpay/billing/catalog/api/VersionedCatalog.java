/**
 * @file VersionedCatalog.java
 * @author Krisna Pranav
 * @brief Versioned Catalog
 * @version 1.0
 * @date 2025-01-18
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.catalog.api;

import java.util.Date;
import java.util.List;

public interface VersionedCatalog {
    public String getCatalogName();

    public List<StaticCatalog> getVersions();

    public StaticCatalog getCurrentVersion();

    public StaticCatalog getVersion(Date targetDate);
}