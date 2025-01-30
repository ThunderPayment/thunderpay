/**
 * @file PlanPhasePriceOverride.java
 * @author Krisna Pranav
 * @brief Plan Phase Price Override
 * @version 1.0
 * @date 2025-01-30
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 */

package org.thunderpay.billing.catalog.api;

import java.math.BigDecimal;
import java.util.List;

public interface PlanPhasePriceOverride {
    public String getPhaseName();

    public PlanPhaseSpecifier getPlanPhaseSpecifier();

    public Currency getCurrency();

    public BigDecimal getFixedPrice();

    public BigDecimal getRecurringPrice();

    public List<UsagePriceOverride> getUsagePriceOverrides();
}