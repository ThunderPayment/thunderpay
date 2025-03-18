/**
 * @file CaseChange.java
 * @author Krisna Pranav
 * @brief Case Change
 * @version 1.0
 * @date 2025-03-18
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.catalog.api.rules;

import org.thunderpay.billing.catalog.api.BillingPeriod;
import org.thunderpay.billing.catalog.api.PhaseType;
import org.thunderpay.billing.catalog.api.PriceList;
import org.thunderpay.billing.catalog.api.Product;
import org.thunderpay.billing.catalog.api.ProductCategory;
import org.thunderpay.billing.catalog.api.StaticCatalog;

public interface CaseChange {

    public StaticCatalog getCatalog();

    public PhaseType getPhaseType();

    public Product getFromProduct();

    public ProductCategory getFromProductCategory();

    public BillingPeriod getFromBillingPeriod();

    public PriceList getFromPriceList();

    public Product getToProduct();

    public ProductCategory getToProductCategory();

    public BillingPeriod getToBillingPeriod();

    public PriceList getToPriceList();
}