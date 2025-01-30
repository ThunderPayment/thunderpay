/**
 * @file MutableStaticCatalog
 * @author Krisna Pranav
 * @brief Mutable Static Catalog
 * @version 1.0
 * @date 2025-01-30
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.catalog.api;

public interface MutableStaticCatalog extends StaticCatalog {
    public void addCurrency(Currency currency) throws CatalogApiException;

    public void addProduct(Product product) throws CatalogApiException;
}
