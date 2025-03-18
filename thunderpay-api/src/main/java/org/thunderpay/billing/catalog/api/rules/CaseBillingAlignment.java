/**
 * @file CaseBillingAlignment.java
 * @author Krisna Pranav
 * @brief Case Billing Alignment
 * @version 1.0
 * @date 2025-03-18
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.catalog.api.rules;

import org.thunderpay.billing.catalog.api.BillingAlignment;

public interface CaseBillingAlignment extends CasePhase {
    public BillingAlignment getBillingAlignment();
}