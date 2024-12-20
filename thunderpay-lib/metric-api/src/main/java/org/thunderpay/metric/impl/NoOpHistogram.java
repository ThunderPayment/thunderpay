/**
 * @file NoOpHistogram.java
 * @author Krisna Pranav
 * @brief Histogram
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.metric.impl;

import java.util.concurrent.atomic.LongAdder;
import org.thunderpay.metric.api.Histogram;
import org.thunderpay.metric.api.Snapshot;

public class NoOpHistogram implements Histogram {
    private final LongAdder count = new LongAdder();

    @Override
    public void update(final long value) {
        count.increment();
    }

    @Override
    public long getCount() {
        return count.sum();
    }

    @Override
    public Snapshot getSnapshot() {
        return null;
    }
}
