/**
 * @file MetricRegistry
 * @author Krisna Pranav
 * @brief Metric Registry
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.metric.api;

import java.util.Map;

public interface MetricRegistry {
    public Counter counter(String name);

    <T> Gauge<T> gauge(String name, Gauge<T> supplier);

    Histogram histogram(String name);

    public Meter meter(String name);

    Timer timer(String name);

    boolean remove(String name);

    Map<String, ?> getMetrics();

    Map<String, Counter> getCounters();

    Map<String, Histogram> getHistograms();

    Map<String, Gauge<?>> getGauges();

    Map<String, Meter> getMeters();

    Map<String, Timer> getTimers();

}
