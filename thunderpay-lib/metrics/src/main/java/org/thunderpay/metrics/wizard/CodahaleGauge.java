/**
 * @file CodahaleGauge.java
 * @author Krisna Pranav
 * @brief Codahale Gauge
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.metrics.wizard;

import org.thunderpay.metric.api.Gauge;

public class CodahaleGauge<T> implements com.codahale.metrics.Gauge<T> {
    private final Gauge<T> gauge;

    public CodahaleGauge(final Gauge<T> gauge) {
        this.gauge = gauge;
    }

    @Override
    public T getValue() {
        return gauge.getValue();
    }
}
