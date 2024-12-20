/**
 * @file NoOpTimer.java
 * @author Krisna Pranav
 * @brief Timer
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.metric.impl;

import java.util.concurrent.TimeUnit;
import org.thunderpay.metric.api.Histogram;
import org.thunderpay.metric.api.Meter;
import org.thunderpay.metric.api.Snapshot;
import org.thunderpay.metric.api.Timer;

public class NoOpTimer implements Timer {
    private final Meter meter;
    private final Histogram histogram;

    public NoOpTimer() {
        this.meter = new NoOpMeter();
        this.histogram = new NoOpHistogram();
    }

    @Override
    public void update(final long duration, final TimeUnit unit) {
        if (duration >= 0) {
            histogram.update(unit.toNanos(duration));
            meter.mark(1);
        }
    }

    @Override
    public long getCount() {
        return histogram.getCount();
    }

    @Override
    public double getFifteenMinuteRate() {
        return meter.getFifteenMinuteRate();
    }

    @Override
    public double getFiveMinuteRate() {
        return meter.getFiveMinuteRate();
    }

    @Override
    public double getMeanRate() {
        return meter.getMeanRate();
    }

    @Override
    public double getOneMinuteRate() {
        return meter.getOneMinuteRate();
    }

    @Override
    public Snapshot getSnapshot() {
        return histogram.getSnapshot();
    }
}
