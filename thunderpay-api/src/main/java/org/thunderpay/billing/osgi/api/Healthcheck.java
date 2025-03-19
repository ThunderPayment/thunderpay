/**
 * @file Healthcheck.java
 * @author Krisna Pranav
 * @brief Health check
 * @version 1.0
 * @date 2025-03-19
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.osgi.api;

import java.util.Collections;
import java.util.Map;
import org.thunderpay.billing.tenant.api.Tenant;

public interface Healthcheck {

    public HealthStatus getHealthStatus(final Tenant tenant, final Map properties);

    public class HealthStatus {

        private final boolean healthy;
        private final Map details;

        public HealthStatus(final boolean healthy, final Map details) {
            this.details = Map.copyOf(details);
            this.healthy = healthy;
        }

        public static HealthStatus healthy() {
            return new HealthStatus(true, Collections.EMPTY_MAP);
        }

        public static HealthStatus healthy(final String message) {
            return new HealthStatus(true, Collections.singletonMap("message", message));
        }

        public static HealthStatus unHealthy(final String message) {
            return new HealthStatus(false, Collections.singletonMap("message", message));
        }

        public boolean isHealthy() {
            return healthy;
        }

        public Map getDetails() {
            return Map.copyOf(details);
        }
    }
}