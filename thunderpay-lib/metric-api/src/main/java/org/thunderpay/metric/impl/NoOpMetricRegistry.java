/**
 * @file NoOpMetricRegistry.java
 * @author Krisna Pranav
 * @brief Metric Registry
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.metric.impl;

import java.util.HashMap;
import java.util.Map;
import org.thunderpay.metric.api.Counter;
import org.thunderpay.metric.api.Gauge;
import org.thunderpay.metric.api.Histogram;
import org.thunderpay.metric.api.Meter;
import org.thunderpay.metric.api.MetricRegistry;
import org.thunderpay.metric.api.Timer;

public class NoOpMetricRegistry implements MetricRegistry {
    private final Map<String, Counter> counters = new HashMap<>();
    private final Map<String, Gauge<?>> gauges = new HashMap<>();
    private final Map<String, Meter> meters = new HashMap<>();
    private final Map<String, Histogram> histograms = new HashMap<>();
    private final Map<String, Timer> timers = new HashMap<>();

    @Override
    public Counter counter(final String name) {
        final Counter counter = new NoOpCounter();
        counters.put(name, counter);
        return counter;
    }

    @Override
    public<T> Gauge<T> gauge(final String name, final Gauge<T> gauge) {
        gauge.put(name, gauge);
        return gauge;
    }

    @Override
    public Meter meter(final String name) {
        final Meter meter = new NoOpMeter();
        meters.put(name, eter);
        return meter;
    }

    @Override
    public Histogram histogram(final String name) {
        final Histogram histogram = new NoOpHistogram();
        histograms.put(name, histogram);
        return histogram;
    }

    @Override
    public Timer timer(final String name) {
        final Timer timer = new NoOpTimer();
        timers.put(name, timer);
        return timer;
    }

    @Override
    public Map<String, Gauge<?>> getGauges() {
        return new HashMap<>(gauges);
    }

    @Override
    public Map<String, Counter> getCounters() {
        return new HashMap<>(counters);
    }

    @Override
    public Map<String, Histogram> getHistograms() {
        return new HashMap<>(histograms);
    }

    @Override
    public Map<String, Meter> getMeters() {
        return new HashMap<>(meters);
    }

    @Override
    public Map<String, Timer> getTimers() {
        return new HashMap<>(timers);
    }
}
