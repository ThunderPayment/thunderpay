/**
 * @file CodahaleTimer.java
 * @author Krisna Pranav
 * @brief Codahale Timer
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.metrics.wizard;

import org.thunderpay.metric.api.Timer;
import com.codahale.metrics.Metered;
import com.codahale.metrics.Sampling;
import com.codahale.metrics.Snapshot;
import com.codahale.metrics.UniformSnapshot;

public class CodahaleTimer implements Metered, Sampling {

    private final Timer timer;

    public CodahaleTimer(final Timer timer) {
        this.timer = timer;
    }

    @Override
    public long getCount() {
        return timer.getCount();
    }

    @Override
    public double getFifteenMinuteRate() {
        return timer.getFifteenMinuteRate();
    }

    @Override
    public double getFiveMinuteRate() {
        return timer.getFiveMinuteRate();
    }

    @Override
    public double getMeanRate() {
        return timer.getMeanRate();
    }

    @Override
    public double getOneMinuteRate() {
        return timer.getOneMinuteRate();
    }

    @Override
    public Snapshot getSnapshot() {
        return new UniformSnapshot(timer.getSnapshot().getValues());
    }
}