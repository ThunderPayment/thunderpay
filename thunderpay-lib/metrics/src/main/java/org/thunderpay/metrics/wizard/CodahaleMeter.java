/**
 * @file CodahaleMeter.java
 * @author Krisna Pranav
 * @brief Codahale Meter
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.metrics.wizard;

import org.thunderpay.metric.api.Meter;
import com.codahale.metrics.Metered;

public class CodahaleMeter implements Metered {
    private final Meter meter;

    public CodahaleMeter(final Meter meter) {
        this.meter = meter;
    }

    @Override
    public long getCount() {
        return meter.getCount();
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
}
