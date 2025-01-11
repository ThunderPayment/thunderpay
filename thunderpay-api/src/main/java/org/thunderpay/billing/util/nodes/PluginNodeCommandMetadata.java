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
    public PluginNodeCommandMetadata(@JsonProperty(PLUGIN_KEY) final String pluginKey, @JsonProperty(PLUGIN_NAME) final String pluginName, @JsonProperty(PLUGIN_VERSION) final String pluginVersion, @JsonProperty("properties") final List<NodeCommandProperty> properties) {
        super(properties);
        this.pluginKey = pluginKey;
        this.pluginName = pluginName;
        this.pluginVersion = pluginVersion;
    }

    public String getPluginKey() {
        return pluginKey;
    }

    public String getPluginName() {
        return pluginName;
    }

    public String getPluginVersion() {
        return pluginVersion;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PluginNodeCommandMetadata{");
        sb.append("pluginKey='").append(pluginKey).append('\'');
        sb.append(", pluginName='").append(pluginName).append('\'');
        sb.append(", pluginVersion='").append(pluginVersion).append('\'');
        sb.append(", properties=").append(getProperties());
        sb.append('}');
        return sb.toString();
    }
}