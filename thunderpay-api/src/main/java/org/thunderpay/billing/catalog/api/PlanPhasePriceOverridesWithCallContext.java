/**
 * @file PlanPhasePriceOverrideWithCallContext
 * @author Krisna Pranav
 * @brief Plan Phase Price Override
 * @version 1.0
 * @date 2025-01-30
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 */

package org.thunderpay.billing.catalog.api;

import java.util.List;

import org.thunderpay.billing.util.callcontext.CallContext;

public interface PlanPhasePriceOverridesWithCallContext {
    public List<PlanPhasePriceOverride> getOverrides();

    public CallContext getCallContext();
}