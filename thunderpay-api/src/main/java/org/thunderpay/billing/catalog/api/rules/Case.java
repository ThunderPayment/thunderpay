/**
 * @file Case.java
 * @author Krisna Pranav
 * @brief Cases
 * @version 1.0
 * @date 2025-03-18
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.catalog.api.rules;

import org.thunderpay.billing.catalog.api.BillingPeriod;
import org.thunderpay.billing.catalog.api.PriceList;
import org.thunderpay.billing.catalog.api.Product;
import org.thunderpay.billing.catalog.api.ProductCategory;
import org.thunderpay.billing.catalog.api.StaticCatalog;

public interface Case {

    public StaticCatalog getCatalog();

    public Product getProduct();

    public ProductCategory getProductCategory();

    public BillingPeriod getBillingPeriod();

    public PriceList getPriceList();
}