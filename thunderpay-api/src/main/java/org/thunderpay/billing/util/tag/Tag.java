/**
 * @file Tag
 * @author Krisna Pranav
 * @brief Tag
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.util.tag;

import java.util.UUID;
import org.thunderpay.billing.ObjectType;
import org.thunderpay.billing.util.entity.Entity;

public interface Tag extends Entity {

    UUID getTagDefinitionId();

    ObjectType getObjectType();

    UUID getObjectId();
}