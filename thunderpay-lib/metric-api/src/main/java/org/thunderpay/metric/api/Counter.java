/**
 * @file Counter.java
 * @author Krisna Pranav
 * @brief Counter
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.metric.api;

public interface Counter extends Metric, Counting {
    void inc(long n);

    default void dec(final long n) {
        inc(-n);
    }
}