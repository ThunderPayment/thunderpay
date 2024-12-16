/**
 * @file UnhealthyResult
 * @author Krisna Pranav
 * @brief Unhealthy Result
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */


package org.thunderpay.health.impl;

import org.thunderpay.health.api.Result;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class UnhealthyResult implements Result {
    private final String message;
    private final Throwable error;
    private final long time;
    private final Map<String, Object> details;

    public UnhealthyResult(final String message, final Throwable error, final long time, final Map<String, Object> details) {
        this.message = message;
        this.error = new Throwable(error);
        this.time = time;
        this.details = details == null ? Collections.emptyMap() : new HashMap<>(details);
    }

    @Override
    public boolean isHealthy() {
        return false;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Throwable getError() {
        return new Throwable(error);
    }

    @Override
    public long getTime() {
        return time;
    }

    @Override
    public Map<String, Object> getDetails() {
        return new HashMap<>(details);
    }
}