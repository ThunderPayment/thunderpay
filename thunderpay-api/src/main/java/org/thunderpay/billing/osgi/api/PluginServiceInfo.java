/**
 * @file PluginServiceInfo.java
 * @author Krisna Pranav
 * @brief Plugin Service Info
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.osgi.api;

public interface PluginServiceInfo {
    public String getServiceTypeName();

    public String getRegistrationName();
}
