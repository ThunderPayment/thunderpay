/**
 * @file CodahaleCounter.java
 * @author Krisna Pranav
 * @brief Codahale Counter
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.metrics.wizard;

import org.thunderpay.metric.api.Counter;
import com.codahale.metrics.Counting;
import com.codahale.metrics.Metric;

public class CodahaleCounter implements Metric, Counting {
    private final Counter counter;

    public CodahaleCounter(final Counter counter) {
        this.counter = counter;
    }

    @Override
    public long getCount() {
        return counter.getCount();
    }
}