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

import org.thunderpay.billing.catalog.api.PhaseType;

import java.util.UUID;

public interface EntitlementAOStatusDryRun {
    public UUID getId();

    public PhaseType getPhaseType();

    public enum DryRunChangeReason {
        AO_INCLUDED_IN_NEW_PLAN,
        AO_NOT_AVAILABLE_IN_NEW_PLAN
    }
}