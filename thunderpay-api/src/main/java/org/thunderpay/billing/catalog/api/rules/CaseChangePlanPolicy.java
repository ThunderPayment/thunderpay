/**
 * @file CaseChangePlanPolicy.java
 * @author Krisna Pranav
 * @brief Case Change Plan Policy
 * @version 1.0
 * @date 2025-03-18
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.catalog.api.rules;

import org.thunderpay.billing.catalog.api.BillingActionPolicy;

public interface CaseChangePlanPolicy extends CaseChange {
    public BillingActionPolicy getBillingActionPolicy();
}