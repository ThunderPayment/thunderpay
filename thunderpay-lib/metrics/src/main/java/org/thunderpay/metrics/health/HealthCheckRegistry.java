/**
 * @file HealthCheckRegistry.java
 * @author Krisna Pranav
 * @brief Health check Registry
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.metrics.health;

import org.thunderpay.health.api.HealthCheck;
import org.thunderpay.health.api.HealthCheckRegistry;
import org.thunderpay.health.api.Result;
import org.thunderpay.health.impl.UnhealthyResultBuilder;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ThunderHealthCheckRegistry implements HealthCheckRegistry {
    private static final Logger logger = LoggerFactory.getLogger(HealthCheckRegistry.class);

    private final ConcurrentMap<String, HealthCheck> healthChecks = new ConcurrentHashMap<>();

    @Inject
    public void initialize(final Set<HealthCheck> healthChecks) {
        for (final HealthCheck healthCheck : healthChecks) {
            register(healthCheck.getClass().getName(), healthCheck);
        }
    }

    @Override
    public Set<String> getNames() {
        return Collections.unmodifiableSortedSet(new TreeSet<>(healthChecks.keySet()));
    }

    @Override
    public Result runHealthCheck(final String name) throws NoSuchElementException {
        final HealthCheck healthCheck = healthChecks.get(name);

        if (healthCheck == null) {
            throw new NoSuchElementException("No check named" + name + "exists");
        }

        final Result result = execute(healthCheck);
        logUnHealthyResult(name, result);

        return result;
    }

    public Result execute(final HealthCheck healthCheck) {
        try {
            return healthCheck.check();
        } catch (final Exception e) {
            return new UnhealthyResultBuilder().setError(e).createUnhealthyResult();
        }
    }
}
