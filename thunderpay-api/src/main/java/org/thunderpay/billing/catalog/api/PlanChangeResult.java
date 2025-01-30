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

    public PlanChangeResult(final PriceList newPriceList, final BillingActionPolicy policy, final PlanAlignmentChange alignment) {
        super();
        this.newPriceList = newPriceList;
        this.policy = policy;
        this.alignment = alignment;
    }

    public PriceList getNewPriceList() {
        return newPriceList;
    }

    public BillingActionPolicy getPolicy() {
        return policy;
    }

    public PlanAlignmentChange getAlignment() {
        return alignment;
    }
}
