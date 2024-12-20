/**
 * @file NoOpMeter.java
 * @author Krisna Pranav
 * @brief Meter
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.metric.impl;

import java.util.concurrent.atomic.LongAdder;
import org.thunderpay.metric.api.Meter;

public class NoOpMeter implements Meter {
    private final LongAdder count = new LongAdder();

    @Override
    public long getCount() {
        return count.sum();
    }

    @Override
    public void mark(final long n) {
        count.add(n);
    }

    @Override
    public double getFifteenMinuteRate() {
        return 0;
    }
}
