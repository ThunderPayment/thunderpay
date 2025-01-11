/**
 * @file NodeCommand.java
 * @author Krisna Pranav
 * @brief Node Command
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.util.nodes;

public interface NodeCommand {
    public boolean isSystemCommandType();

    public String getNodeCommandType();
}
