/**
 * @file PluginJavaConfig.java
 * @author Krisna Pranav
 * @brief Plugin Java Config
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.osgi.api.config;

public interface PluginJavaConfig extends PluginConfig {
    public String getBundleJarPath();
}
