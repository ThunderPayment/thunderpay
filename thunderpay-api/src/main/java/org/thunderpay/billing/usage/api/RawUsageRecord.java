/**
 * @file RawUsageRecord.java
 * @author Krisna Pranav
 * @brief RAw Usage Recorder
 * @version 1.0
 * @date 2025-03-18
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.usage.api;

import java.math.BigDecimal;
import java.util.UUID;
import org.joda.time.DateTime;

public interface RawUsageRecord {

    UUID getSubscriptionId();

    DateTime getDate();

    String getUnitType();

    BigDecimal getAmount();

    String getTrackingId();
}