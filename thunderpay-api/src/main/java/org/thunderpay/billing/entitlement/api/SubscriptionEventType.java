/**
 * @file SubscriptionEventType.java
 * @author Krisna Pranav
 * @brief Subscription Event Type
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.entitlement.api;

import org.thunderpay.billing.ObjectType;

public enum SubscriptionEventType {
    private ObjectType objectType;

    SubscriptionEventType(ObjectType type) {
        this.objectType = type;
    }

    public ObjectType getObjectType() {
        return objectType;
    }
}
