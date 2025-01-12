/**
 * @file PluginInfo.java
 * @author Krisna Pranav
 * @brief Plugins Info Api
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.osgi.api;

import java.util.Set;

public interface PluginInfo {
    public String getPluginKey();

    public String getBundleSymbolicName();

    public String getPluginName();

    public String getVersion();

    public PluginState getPluginState();

    public Set<PluginServiceInfo> getServices();

    public boolean isSelectedForStart();

}
