/**
 * @file SubscriptionBundleTimeline.java
 * @author Krisna Pranav
 * @version 1.0
 * @date 2025-01-19
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.entitlement.api;

import java.util.List;
import java.util.UUID;

public interface SubscriptionBundleTimeline {
    public UUID getAccountId();
    public UUID getBundleId();
    public String getExternalKey();

    public List<SubscriptionEvent> getSubscriptionEvents();
}