/**
 * @file GatewayNotification.java
 * @author Krisna Pranav
 * @brief Gateway Notifications
 * @version 1.0
 * @date 2025-03-22
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.payment.plugin.api;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.thunderpay.billing.payment.api.PluginProperty;

public interface GatewayNotification {
    public UUID getKbPaymentId();

    public int getStatus();

    public String getEntity();

    public Map<String, List<String>> getHeaders();

    public List<PluginProperty> getProperties();
}