/**
 * @file HealthyResult
 * @author Krisna Pranav
 * @brief Healthy Result
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

public class HealthyResult implements Result {
    private final String message;
    private final long time;
    private final Map<String, Object> details;

    public HealthyResult(final String message, final long time, final Map<String, Object> details) {
        this.message = message;
        this.time = time;
        this.details = details == null ? Collections.emptyMap() : new HashMap<>(details);
    }

    @Override
    public boolean isHealthy() {
        return true;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Throwable getError() {
        return null;
    }

    @Override
    public long getTime() {
        return time;
    }

    @Override
    public Map<String, Object> getDetails() {
        return new HashMap<>(details);
    }

    @Override
    public String toString() {
        return "HealthyResult{" + "message='" + message + '\'' + ", time=" + time +  ", details=" + details +  '}';
    }
}
