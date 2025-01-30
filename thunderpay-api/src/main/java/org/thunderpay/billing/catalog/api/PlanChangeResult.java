/**
 * @file PlanChangeResult.java
 * @author Krisna Pranav
 * @brief Plan Change Result
 * @version 1.0
 * @date 2025-01-30
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 */

package org.thunderpay.billing.catalog.api;

public class PlanChangeResult {
    private final PriceList newPriceList;
    private final BillingActionPolicy policy;
    private final PlanAlignmentChange alignment;

    public PlanChangeResult(final PriceList newPriceList, final BillingActionPolicy policy) {
        super();
        this.newPriceList = newPriceList;
        this.policy = policy;
    }

    public PriceLst getNewPriceLst() {
        return newPriceList;
    }
}
