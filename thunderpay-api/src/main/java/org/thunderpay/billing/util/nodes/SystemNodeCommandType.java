/**
 * @file SystemNodeCommandType.java
 * @author Krisna Pranav
 * @brief System Node Command Type
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.util.nodes;

public enum SystemNodeCommandType {
    INSTALL_PLUGIN(PluginNodeCommandMetadata.class),
    UNINSTALL_PLUGIN(PluginNodeCommandMetadata.class),
    START_PLUGIN(PluginNodeCommandMetadata.class),
    STOP_PLUGIN(PluginNodeCommandMetadata.class),
    RESTART_PLUGIN(PluginNodeCommandMetadata.class);

    Class<? extends NodeCommandMetadata> commandMetadataClass;

    SystemNodeCommandType(final Class<? extends NodeCommandMetadata> commandMetadataClass) {
        this.commandMetadataClass = commandMetadataClass;
    }

    public Class<? extends NodeCommandMetadata> getCommandMetadataClass() {
        return commandMetadataClass;
    }
}