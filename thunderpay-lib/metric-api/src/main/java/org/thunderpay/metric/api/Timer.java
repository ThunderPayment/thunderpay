/**
 * @file Timer.java
 * @author Krisna Pranav
 * @brief Custom Timer funcs
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.metric.api;

import java.util.concurrent.TimeUnit;

public interface Timer extends Metered, Sampling {
    long getCount();

    void update(long duration, TimeUnit unit);
}
