/**
 * @file Duration.java
 * @author Krisna Pranav
 * @brief Duration
 * @version 1.0
 * @date 2025-01-21
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */


package org.thunderpay.billing.catalog.api;

public interface Duration {
    public TimeUnit getUnit();

    public int getNumber();

    public Period toJodaPeriod();
}
