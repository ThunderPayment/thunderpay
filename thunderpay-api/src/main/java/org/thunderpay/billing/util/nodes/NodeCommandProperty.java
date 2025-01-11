/**
 * @file NodeCommandProperty.java
 * @author Krisna Pranav
 * @brief Node Command Property
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.util.nodes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NodeCommandProperty {

    private String key;
    private Object value;

    public NodeCommandProperty() { }

    @JsonCreator
    public NodeCommandProperty(@JsonProperty("key") final String key, @JsonProperty("value") final Object value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("NodeCommandProperty{");
        sb.append("key='").append(key).append('\'');
        sb.append(", value=").append(value);
        sb.append('}');
        return sb.toString();
    }
}
