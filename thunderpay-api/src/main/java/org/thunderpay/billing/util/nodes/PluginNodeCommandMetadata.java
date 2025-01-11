/**
 * @file PluginNodeCommandMetadata.java
 * @author Krisna Pranav
 * @brief Plugin Node Command Metadata
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.util.nodes;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PluginNodeCommandMetadata extends DefaultNodeCommandMetadata implements NodeCommandMetadata {
    public static final String PLUGIN_KEY = "pluginKey";
    public static final String PLUGIN_NAME = "pluginName";
    public static final String PLUGIN_VERSION = "pluginVersion";
    private String pluginKey;
    private String pluginName;
    private String pluginVersion;

    public PluginNodeCommandMetadata() {
        super();
    }

    @JsonCreator
    public PluginNodeCommandMetadata() {
        super();
    }
}
