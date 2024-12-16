/**
 * @file UnhealthyResultBuilder
 * @author Krisna Pranav
 * @brief Unhealthy Result Builder
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.health.impl;

import java.util.Collections;
import java.util.Map;

public class UnhealthyResultBuilder {
    private String message;
    private Throwable error;
    private long time;
    private Map<String, Object> details;

    public UnhealthyResultBuilder setMessage(final String message) {
        this.message = message;
        return this;
    }

    public UnhealthyResultBuilder setError(final Throwable error) {
        this.error = new Throwable(error);
        return this;
    }

    public UnhealthyResultBuilder setTime(final long time) {
        this.time = time;
        return this;
    }

    public UnhealthyResultBuilder setDetails(final Map<String, Object> details) {
        this.details = Collections.unmodifiableMap(details);
        return this;
    }

    public UnhealthyResult createUnhealthyResult() {
        return new UnhealthyResult(message, error, time, details);
    }
}
