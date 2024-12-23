/**
 * @file CodahaleHistogram.java
 * @author Krisna Pranav
 * @brief Codahale Histogram
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.metrics.wizard;

import org.thunderpay.metric.api.Histogram;
import com.codahale.metrics.Counting;
import com.codahale.metrics.Metric;
import com.codahale.metrics.Sampling;
import com.codahale.metrics.Snapshot;
import com.codahale.metrics.UniformSnapshot;

public class CodahaleHistogram implements Metric, Sampling, Counting {

    private final Histogram histogram;

    public CodahaleHistogram(final Histogram histogram) {
        this.histogram = histogram;
    }

    @Override
    public long getCount() {
        return histogram.getCount();
    }

    @Override
    public Snapshot getSnapshot() {
        return new UniformSnapshot(histogram.getSnapshot().getValues());
    }
}