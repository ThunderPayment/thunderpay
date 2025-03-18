/**
 * @file UnitUsageRecord.java
 * @author Krisna Pranav
 * @brief Unit Usage Record
 * @version 1.0
 * @date 2025-03-18
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.usage.api;

import java.util.List;

public class UnitUsageRecord {

    private final String unitType;

    private final List<UsageRecord> usageRecord;

    public UnitUsageRecord(final String unitType, final List<UsageRecord> usageRecord) {
        this.unitType = unitType;
        this.usageRecord = List.copyOf(usageRecord);
    }

    public String getUnitType() {
        return unitType;
    }

    public List<UsageRecord> getDailyAmount() {
        return List.copyOf(usageRecord);
    }
}