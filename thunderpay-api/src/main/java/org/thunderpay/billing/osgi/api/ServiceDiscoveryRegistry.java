/**
 * @file ServiceDiscoveryRegistry.java
 * @author Krisna Pranav
 * @brief Service Discovery Registry
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.osgi.api;

public interface ServiceDiscoveryRegistry {

    void register();

    void unregister();
}