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

    private final org.thunderpay.metric.api.MetricRegistry thunderpayMetricRegistry;

    public CodahaleMetricRegistry(final org.thunderpay.metric.api.MetricRegistry thunderpayMetricRegistry) {
        this.thunderpayMetricRegistry = thunderpayMetricRegistry;
    }

    @Override
    public <T extends Metric> T register(final String name, final T metric) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Register via the MetricRegistry instead");
    }

    @Override
    public void registerAll(final MetricSet metrics) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Register via the MetricRegistry instead");
    }

    @Override
    public Counter counter(final String name) {
        throw new UnsupportedOperationException("Register via the MetricRegistry instead");
    }

    @Override
    public Counter counter(final String name, final MetricSupplier<Counter> supplier) {
        throw new UnsupportedOperationException("Register via the MetricRegistry instead");
    }

    @Override
    public Histogram histogram(final String name) {
        throw new UnsupportedOperationException("Register via the MetricRegistry instead");
    }

    @Override
    public Histogram histogram(final String name, final MetricSupplier<Histogram> supplier) {
        throw new UnsupportedOperationException("Register via the MetricRegistry instead");
    }

    @Override
    public Meter meter(final String name) {
        throw new UnsupportedOperationException("Register via the MetricRegistry instead");
    }

    @Override
    public Meter meter(final String name, final MetricSupplier<Meter> supplier) {
        throw new UnsupportedOperationException("Register via the MetricRegistry instead");
    }

    @Override
    public Timer timer(final String name) {
        throw new UnsupportedOperationException("Register via the MetricRegistry instead");
    }

    @Override
    public Timer timer(final String name, final MetricSupplier<Timer> supplier) {
        throw new UnsupportedOperationException("Register via the MetricRegistry instead");
    }

    @Override
    public <T extends Gauge> T gauge(final String name) {
        throw new UnsupportedOperationException("Register via the MetricRegistry instead");
    }

    @Override
    public <T extends Gauge> T gauge(final String name, final MetricSupplier<T> supplier) {
        throw new UnsupportedOperationException("Register via the MetricRegistry instead");
    }

    @Override
    public boolean remove(final String name) {
        throw new UnsupportedOperationException("UnRegister via the MetricRegistry instead");
    }

    @Override
    public void removeMatching(final MetricFilter filter) {
        throw new UnsupportedOperationException("UnRegister via the MetricRegistry instead");
    }

    @Override
    public SortedSet<String> getNames() {
        return Collections.unmodifiableSortedSet(new TreeSet<>(getMetrics().keySet()));
    }

    @Override
    @SuppressWarnings("rawtypes")
    public SortedMap<String, Gauge> getGauges() {
        return getGauges(MetricFilter.ALL);
    }

    @Override
    @SuppressWarnings("rawtypes")
    public SortedMap<String, Gauge> getGauges(final MetricFilter filter) {
        return getMetrics(Gauge.class, filter);
    }

    @Override
    public SortedMap<String, Counter> getCounters() {
        return getCounters(MetricFilter.ALL);
    }

    @Override
    public SortedMap<String, Counter> getCounters(final MetricFilter filter) {
        return getMetrics(Counter.class, filter);
    }

    @Override
    public SortedMap<String, Histogram> getHistograms() {
        return getHistograms(MetricFilter.ALL);
    }

    @Override
    public SortedMap<String, Histogram> getHistograms(final MetricFilter filter) {
        return getMetrics(Histogram.class, filter);
    }

    @Override
    public SortedMap<String, Meter> getMeters() {
        return getMeters(MetricFilter.ALL);
    }

    @Override
    public SortedMap<String, Meter> getMeters(final MetricFilter filter) {
        return getMetrics(Meter.class, filter);
    }

    @Override
    public SortedMap<String, Timer> getTimers() {
        return getTimers(MetricFilter.ALL);
    }

    @Override
    public SortedMap<String, Timer> getTimers(final MetricFilter filter) {
        return getMetrics(Timer.class, filter);
    }

    @SuppressWarnings("unchecked")
    private <T extends Metric> SortedMap<String, T> getMetrics(final Class<T> klass, final MetricFilter filter) {
        final TreeMap<String, T> timers = new TreeMap<>();
        for (final Map.Entry<String, Metric> entry : getMetrics().entrySet()) {
            if (klass.isInstance(entry.getValue()) && filter.matches(entry.getKey(),
                    entry.getValue())) {
                timers.put(entry.getKey(), (T) entry.getValue());
            }
        }
        return Collections.unmodifiableSortedMap(timers);
    }

    @Override
    public void registerAll(final String prefix, final MetricSet metrics) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Register via the MetricRegistry instead");
    }

    @Override
    public Map<String, Metric> getMetrics() {
        final ConcurrentMap<String, Metric> dropwizardMap = new ConcurrentHashMap<>();

        for(final Entry<String, org.thunderpay.metric.api.Counter> entry : thunderpayMetricRegistry.getCounters().entrySet()) {
            dropwizardMap.put(entry.getKey(), new CodahaleCounter(entry.getValue()));
        }

        for(final Entry<String, org.thunderpay.metric.api.Histogram> entry : thunderpayMetricRegistry.getHistograms().entrySet()) {
            dropwizardMap.put(entry.getKey(), new CodahaleHistogram(entry.getValue()));
        }

        for(final Entry<String, org.thunderpay.metric.api.Gauge<?>> entry : thunderpayMetricRegistry.getGauges().entrySet()) {
            dropwizardMap.put(entry.getKey(), new CodahaleGauge(entry.getValue()));
        }

        for(final Entry<String, org.thunderpay.metric.api.Meter> entry : thunderpayMetricRegistry.getMeters().entrySet()) {
            dropwizardMap.put(entry.getKey(), new CodahaleMeter(entry.getValue()));
        }

        for(final Entry<String, org.thunderpay.metric.api.Timer> entry : thunderpayMetricRegistry.getTimers().entrySet()) {
            dropwizardMap.put(entry.getKey(), new CodahaleTimer(entry.getValue()));
        }

        return dropwizardMap;
    }
}