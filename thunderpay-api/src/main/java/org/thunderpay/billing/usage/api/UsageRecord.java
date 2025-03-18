/**
 * @file UsageRecord.java
 * @author Krisna Pranav
 * @brief Usage Record
 * @version 1.0
 * @date 2025-03-18
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.usage.api;

import java.math.BigDecimal;
import org.joda.time.DateTime;

public class UsageRecord {

    private final DateTime recordDate;

    private final BigDecimal amount;

    public UsageRecord(final DateTime recordDate, final BigDecimal amount) {
        this.recordDate = recordDate;
        this.amount = amount;
    }

    public DateTime getDate() {
        return recordDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}