/**
 * @file PlanSpecifier.java
 * @author Krisna Pranav
 * @brief Plan Specifier
 * @version 1.0
 * @date 2025-01-30
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 */

package org.thunderpay.billing.catalog.api;

public class PlanSpecifier {
    private final String planName;
    private final String productName;
    private final BillingPeriod billingPeriod;
    private final String priceListName;

    public PlanSpecifier(final String productName, final BillingPeriod billingPeriod, final String priceListName) {
        super();
        this.planName = null;
        this.productName = productName;
        this.billingPeriod = billingPeriod;
        this.priceListName = priceListName;
    }

    public String getProductName() {
        return productName;
    }

    public BillingPeriod getBillingPeriod() {
        return billingPeriod;
    }

    public String getPriceListName() {
        return priceListName;
    }
}
