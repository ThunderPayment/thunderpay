/**
 * @file NodeInfo.java
 * @author Krisna Pranav
 * @brief Node Info
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.util.nodes;

import org.joda.time.DateTime;
import org.thunderpay.billing.osgi.api.PluginInfo;

public interface NodeInfo {

    public String getNodeName();

    public DateTime getBootTime();

    public DateTime getLastUpdatedDate();

    public String getThunderpayVersion();

    public String getApiVersion();

    public String getPlatformVersion();

    public String getCommonVersion();

    public String getPluginApiVersion();

    public Iterable<PluginInfo> getPluginInfo();
}