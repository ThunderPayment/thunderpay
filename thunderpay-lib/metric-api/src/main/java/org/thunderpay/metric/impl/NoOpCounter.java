/**
 * @file NoOpCounter.java
 * @author Krisna Pranav
 * @brief Counter
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.metric.impl;

import java.util.concurrent.atomic.LongAdder;
import org.thunderpay.metric.api.Counter;

public class NoOpCounter implements Counter {
    private final LongAdder count = new LongAdder();

    @Override
    public long getCount() {
        return count.sum();
    }

    @Override
    public void inc(final long n) {
        count.add(n);
    }
}
