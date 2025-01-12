/**
 * @file PluginConfigServiceApi
 * @author Krisna Pranav
 * @brief Plugin Config Service API
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.osgi.api.config;

import org.thunderpay.billing.ThunderApi;

public interface PluginConfigServiceApi extends ThunderApi {
    public PluginJavaConfig getPluginJavaConfig(long bundleId);
}
