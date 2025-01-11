/**
 * @file NodeCommandMetadata.java
 * @author Krisna Pranav
 * @brief Node Command Meta Data
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.util.nodes;

import java.util.List;

public interface NodeCommandMetadata {
    public List<NodeCommandProperty> getProperties();
}