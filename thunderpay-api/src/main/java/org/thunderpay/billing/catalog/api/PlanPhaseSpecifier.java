/**
 * @file PlanPhaseSpecifier.java
 * @author Krisna Pranav
 * @brief Plan Phase Specifier
 * @version 1.0
 * @date 2025-01-30
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 */

package org.thunderpay.billing.catalog.api;

public class PlanPhaseSpecifier extends PlanSpecifier {
    private final PhaseType phaseType;

    public PlanPhaseSpecifier(final String productName, final BillingPeriod billingPeriod, final String priceListName) {
        this(productName, billingPeriod, priceListName, null);
    }

    public PlanPhaseSpecifier(final String productName, final BillingPeriod billingPeriod, final String priceListName, final PhaseType phaseType) {
        super(productName, billingPeriod, priceListName);
        this.phaseType = phaseType;
    }

    public PlanPhaseSpecifier(final String planName) {
        this(planName, null);
    }

    public PlanPhaseSpecifier(final String planName, final PhaseType phaseType) {
        super(planName);
        this.phaseType = phaseType;
    }

    public PhaseType getPhaseType() {
        return phaseType;
    }
}
