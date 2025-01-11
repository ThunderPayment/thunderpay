/**
 * @file DefaultNodeCommandMetadata.java
 * @author Krisna Pranav
 * @brief Default Node Command Metadata
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

public class DefaultNodeCommandMetadata implements NodeCommandMetadata {

    private List<NodeCommandProperty> properties;

    public DefaultNodeCommandMetadata() {
    }

    @JsonCreator
    public DefaultNodeCommandMetadata(@JsonProperty("properties") final List<NodeCommandProperty> properties) {
        this.properties = List.copyOf(properties);
    }

    @Override
    public List<NodeCommandProperty> getProperties() {
        return List.copyOf(properties);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DefaultNodeCommandMetadata{");
        sb.append("properties=").append(properties);
        sb.append('}');
        return sb.toString();
    }
}