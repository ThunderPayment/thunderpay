/**
 * @file CodahaleMetricRegistry.java
 * @author Krisna Pranav
 * @brief Codahale Metric Registry
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.metrics.wizard;

import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import com.codahale.metrics.Counter;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.Histogram;
import com.codahale.metrics.Meter;
import com.codahale.metrics.Metric;
import com.codahale.metrics.MetricFilter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.MetricSet;
import com.codahale.metrics.Timer;

public class CodahaleMetricRegistry extends MetricRegistry {
    private final org.thunderpay.metric.api.MetricRegistry thunderMetricRegistry;

    public CodahaleMetricRegistry(final org.thunderpay.metric.api.MetricRegistry thunderMetricRegistry) {
        this.thunderMetricRegistry = thunderMetricRegistry;
    }

    @Override
    public<T extends Metric> T register(final String name, final T metric) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Register via metric register instead");
    }

    @Override
    public void registerAll(final MetricSet metrics) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Register via metric register instead");
    }

    @Override
    public Counter counter(final String name) {
        throw new UnsupportedOperationException("Register via metric register instead");
    }

    @Override
    public Counter counter(final String name, final MetricSupplier<Counter> supplier) {
        throw new UnsupportedOperationException("Register via metric register instead");
    }

    @Override
    public Histogram histogram(final String name) {
        throw new UnsupportedOperationException("Register via the metric reigster instead");
    }

    @Override
    public Histogram histogram(final String name, final MetricSupplier<Histogram> supplier) {
        throw new UnsupportedOperationException("Register via the metric reigster instead");
    }

    @Override
    public Meter meter(final String name) {
        throw new UnsupportedOperationException("Register via the metric reigster instead");
    }

    @Override
    public Meter meter(final String name, final MetricSupplier<Meter> supplier) {
        throw new UnsupportedOperationException("Register via the metric reigster instead");
    }
}
