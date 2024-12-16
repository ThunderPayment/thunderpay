/**
 * @file Gauge.java
 * @author Krisna Pranav
 * @brief Gauge
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.metric.api;

public interface Gauge<T> extends Metric {
    T getValue();
}