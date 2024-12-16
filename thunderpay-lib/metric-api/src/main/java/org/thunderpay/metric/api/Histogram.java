/**
 * @file Histogram.java
 * @author Krisna Pranav
 * @brief Histogram
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.metric.api;

public interface Histogram extends Metric, Sampling, Counting {
    void update(long value);
}
