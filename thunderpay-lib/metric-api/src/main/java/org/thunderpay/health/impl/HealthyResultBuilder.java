/**
 * @file HealthyResultBuilder
 * @author Krisna Pranav
 * @brief Healthy Result Builder
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.health.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class HealthyResultBuilder {
    private String message;
    private long time;
    private Map<String, Object> details;

    public HealthyResultBuilder setMessage(final String message) {
        this.message = message;
        return this;
    }

    public HealthyResultBuilder setTime(final long time) {
        this.time = time;
        return this;
    }

    public HealthyResultBuilder setDetails(final Map<String, Object> details) {
        this.details = Collections.unmodifiableMap(details);
        return this;
    }

    public HealthyResult createHealthyResult() {
        return new HealthyResult(message, time, details);
    }
}
