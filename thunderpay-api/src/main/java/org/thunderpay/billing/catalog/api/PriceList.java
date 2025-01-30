/**
 * @file PriceList.java
 * @author Krisna Pranav
 * @brief Price List
 * @version 1.0
 * @date 2025-01-30
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 */

package org.thunderpay.billing.catalog.api;

import java.util.Collection;

public interface PriceList extends CatalogEntity {
    public StaticCatalog getCatalog();

    public Collection<Plan> findPlans(Product product, BillingPeriod period);

    public Collection<Plan> getPlans();
}