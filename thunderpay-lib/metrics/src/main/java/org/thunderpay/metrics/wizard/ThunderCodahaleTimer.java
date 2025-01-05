/**
 * @file CodahaleTimer.java
 * @author Krisna Pranav
 * @brief Codahale Timer
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.metrics.wizard;

import java.util.concurrent.TimeUnit;
import org.thunderpay.metric.api.Snapshot;
import org.thunderpay.metric.api.Timer;

public class ThunderCodahaleTimer implements Timer {
    private final com.codahale.metrics.Timer dwTimer;
    
    public ThunderCodahaleTimer(final com.codahale.metrics.Timer dwTimer) {
        this.dwTimer = dwTimer;
    }

    @Override
    public long getCount() {
        return dwTimer.getCount();
    }

    @Override
    public void update(final long duration, final TimeUnit unit) {
        dwTimer.update(duration, unit);
    }

    @Override
    public double getFifteenMinuteRate() {
        return dwTimer.getFifteenMinuteRate();
    }
}
