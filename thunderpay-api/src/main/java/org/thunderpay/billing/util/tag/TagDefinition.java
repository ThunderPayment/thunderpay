/**
 * @file TagDefinition.java
 * @author Krisna Pranav
 * @brief Tag Definition
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.util.tag;

import java.util.List;
import org.thunderpay.billing.ObjectType;
import org.thunderpay.billing.util.entity.Entity;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public interface TagDefinition extends Entity {
    String getName();
}
