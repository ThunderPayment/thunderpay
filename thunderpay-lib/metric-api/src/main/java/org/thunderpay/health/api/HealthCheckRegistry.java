/**
 * @file HealthCheckRegistry
 * @author Krisna Pranav
 * @brief Health check Registry
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.health.api;

import java.util.NoSuchElementException;
import java.util.Set;

public interface HealthCheckRegistry {
    Set<String> getNames();

    Result runHealthCheck(String name) throws NoSuchElementException;

    void register(String name, HealthCheck healthCheck);
}