/**
 * @file PluginsInfoApi.java
 * @author Krisna Pranav
 * @brief Plugins Info Api
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.osgi.api;

import org.thunderpay.billing.ThunderApi;
import org.thunderpay.billing.osgi.api.config.PluginLanguage;

public interface PluginsInfoApi extends ThunderApi {
    public Iterable<PluginInfo> getPluginsInfo();
}