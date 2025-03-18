/**
 * @file SubscriptionUsageRecord.java
 * @author Krisna Pranav
 * @brief Subscription Usage Record
 * @version 1.0
 * @date 2025-03-18
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.usage.api;

import java.util.List;
import java.util.UUID;

public class SubscriptionUsageRecord {

    private final UUID subscriptionId;

    private final String trackingId;

    private final List<UnitUsageRecord> unitUsageRecord;

    public SubscriptionUsageRecord(final UUID subscriptionId, final String trackingId, final List<UnitUsageRecord> unitUsageRecord) {
        this.subscriptionId = subscriptionId;
        this.trackingId = trackingId;
        this.unitUsageRecord = List.copyOf(unitUsageRecord);
    }

    public UUID getSubscriptionId() {
        return subscriptionId;
    }

    public String getTrackingId() {
        return trackingId;
    }

    public List<UnitUsageRecord> getUnitUsageRecord() {
        return List.copyOf(unitUsageRecord);
    }
}