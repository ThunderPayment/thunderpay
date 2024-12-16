/**
 * @file HealthCheck
 * @author Krisna Pranav
 * @brief Health check
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.health.api;

public interface HealthCheck {
    Result check() throws Exception;
}
