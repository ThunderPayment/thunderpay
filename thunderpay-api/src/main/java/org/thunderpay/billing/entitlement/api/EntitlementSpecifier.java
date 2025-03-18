/**
 * @file EntitlementSpecifier.java
 * @author Krisna Pranav
 * @version 1.0
 * @date 2025-01-19
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.entitlement.api;

import java.util.List;
import org.thunderpay.billing.catalog.api.PlanPhasePriceOverride;
import org.thunderpay.billing.catalog.api.PlanPhaseSpecifier;

public interface EntitlementSpecifier {
    PlanPhaseSpecifier getPlanPhaseSpecifier();

    Integer getBillCycleDay();

    Integer getQuantity();

    String getExternalKey();

    List<PlanPhasePriceOverride> getOverrides();

}