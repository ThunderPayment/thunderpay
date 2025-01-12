/**
 * @file PluginType.java
 * @author Krisna Pranav
 * @brief Plugin Type
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.osgi.api.config;

public enum PluginType {
    PAYMENT,
    NOTIFICATION,
    INVOICE,
    USAGE,
    UNKNOWN
}