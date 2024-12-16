/**
 * @file Snapshot.java
 * @author Krisna Pranav
 * @brief Snapshot
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.metric.api;

import java.io.OutputStream;

public interface Snapshot {
    double getValue(double quantile);

    long[] getValues();

    int size();

    default double getMedian() {
        return getValue(0.5);
    }

    default double get75thPercentile() {
        return getValue(0.75);
    }

    default double get95thPercentile() {
        return getValue(0.95);
    }

    default double get98thPercentile() {
        return getValue(0.98);
    }

    default double get99thPercentile() {
        return getValue(0.99);
    }

    default double get999thPercentile() {
        return getValue(0.999);
    }

    long getMax();

    double getMean();

    long getMin();

    double getStdDev();

    void dump(OutputStream output);
}
