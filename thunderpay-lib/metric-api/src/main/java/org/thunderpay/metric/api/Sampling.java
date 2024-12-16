/**
 * @file Sampling.java
 * @author Krisna Pranav
 * @brief Sampling
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.metric.api;

public interface Sampling {
    Snapshot getSnapshot();
}