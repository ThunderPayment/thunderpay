/**
 * @file EntitlementAOStatusDryRun.java
 * @author Krisna Pranav
 * @version 1.0
 * @date 2025-01-19
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.entitlement.api;

import java.util.UUID;
import org.thunderpay.billing.catalog.api.BillingPeriod;
import org.thunderpay.billing.catalog.api.PhaseType;

public interface EntitlementAOStatusDryRun {

    public UUID getId();

    public String getProductName();

    public BillingPeriod getBillingPeriod();

    public String getPriceList();

    public PhaseType getPhaseType();

    public DryRunChangeReason getReason();

    public enum DryRunChangeReason {
        AO_INCLUDED_IN_NEW_PLAN,
        AO_NOT_AVAILABLE_IN_NEW_PLAN,
        AO_AVAILABLE_IN_NEW_PLAN
    }
}