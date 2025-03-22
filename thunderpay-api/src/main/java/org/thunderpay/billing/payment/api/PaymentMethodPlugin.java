/**
 * @file PaymentMethodPlugin.java
 * @author Krisna Pranav
 * @brief Payment Method Plugin
 * @version 1.0
 * @date 2025-03-19
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.payment.api;

import java.util.List;
import java.util.UUID;

public interface PaymentMethodPlugin {

    public UUID getKbPaymentMethodId();

    public String getExternalPaymentMethodId();

    public boolean isDefaultPaymentMethod();

    public List<PluginProperty> getProperties();
}