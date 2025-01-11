/**
 * @file CustomField.java
 * @author Krisna Pranav
 * @brief Custom Field
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.util.customfield;

import java.util.UUID;
import org.thunderpay.billing.ObjectType;
import org.thunderpay.billing.util.entity.Entity;

public interface CustomField extends Entity {

    public UUID getObjectId();

    public ObjectType getObjectType();

    public String getFieldName();

    public String getFieldValue();

}