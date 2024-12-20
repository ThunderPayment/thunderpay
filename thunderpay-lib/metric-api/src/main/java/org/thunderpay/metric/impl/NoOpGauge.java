/**
 * @file NoOpGauge.java
 * @author Krisna Pranav
 * @brief Gauge
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.metric.impl;

import org.thunderpay.metric.api.Gauge;

public class NoOpGauge<T> implements Gauge {
    private final T value;

    public NoOpGauge(final T value) {
        this.value = value;
    }

    @Override
    public T getValue() {
        return value;
    }
}