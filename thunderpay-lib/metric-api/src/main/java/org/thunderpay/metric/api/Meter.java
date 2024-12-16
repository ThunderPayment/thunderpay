/**
 * @file Meter.java
 * @author Krisna Pranav
 * @brief Meter
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.metric.api;

public interface Meter extends Metered {
    void mark(long n);
}