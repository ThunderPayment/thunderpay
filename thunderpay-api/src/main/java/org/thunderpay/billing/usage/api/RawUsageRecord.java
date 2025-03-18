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