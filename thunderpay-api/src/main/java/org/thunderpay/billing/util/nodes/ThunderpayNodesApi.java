/**
 * @file ThunderpayNodesApi.java
 * @author Krisna Pranav
 * @version 1.0
 * @date 2024-03-19
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.util.nodes;

import org.thunderpay.billing.ThunderApi;
import org.thunderpay.billing.osgi.api.PluginInfo;
import org.thunderpay.billing.security.RequiresPermissions;
import static org.thunderpay.billing.security.Permission.ADMIN_CAN_TRIGGER_COMMAND;

public interface ThunderpayNodesApi extends ThunderApi {

    public Iterable<NodeInfo> getNodesInfo();

    public NodeInfo getCurrentNodeInfo();

    @RequiresPermissions(ADMIN_CAN_TRIGGER_COMMAND)
    public void triggerNodeCommand(NodeCommand nodeCommand, boolean localNodeOnly);

    public void notifyPluginChanged(PluginInfo plugin, Iterable<PluginInfo> latestPlugins);
}