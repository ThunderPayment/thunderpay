/**
 * @file CaseChangePlanAlignment.java
 * @author Krisna Pranav
 * @brief Case Change Plan Alignment
 * @version 1.0
 * @date 2025-03-18
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.catalog.api.rules;

import org.thunderpay.billing.catalog.api.PlanAlignmentChange;

public interface CaseChangePlanAlignment extends CaseChange {
    public PlanAlignmentChange getAlignment();
}