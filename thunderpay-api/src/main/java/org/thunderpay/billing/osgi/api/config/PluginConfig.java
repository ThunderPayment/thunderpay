/**
 * @file PluginConfig.java
 * @author Krisna Pranav
 * @brief Plugin Config
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.osgi.api.config;

import java.io.File;

public interface PluginConfig extends Comparable<PluginConfig> {
    public String getPluginKey();

    public String getPluginName();

    public PluginType getPluginType();

    public String getVersion();

    public String getPluginVersionnedName();

    public File getPluginVersionRoot();

    public PluginLanguage getPluginLanguage();

    public boolean isSelectedForStart();

    public boolean isDisabled();
}