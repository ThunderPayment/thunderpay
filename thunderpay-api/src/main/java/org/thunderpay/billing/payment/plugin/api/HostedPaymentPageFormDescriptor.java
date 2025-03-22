/**
 * @file HostedPaymentPageFormDescriptior.java
 * @author Krisna Pranav
 * @brief Hosted Payment Page Form Descriptor
 * @version 1.0
 * @date 2025-03-22
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.payment.plugin.api;

import java.util.List;
import java.util.UUID;
import org.thunderpay.billing.payment.api.PluginProperty;

public interface HostedPaymentPageFormDescriptor {

    public UUID getKbAccountId();

    public String getFormMethod();

    public String getFormUrl();

    public List<PluginProperty> getFormFields();

    public List<PluginProperty> getProperties();
}