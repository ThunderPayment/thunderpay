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
    START_ENTITLEMENT(ObjectType.BLOCKING_STATES),
    START_BILLING(ObjectType.SUBSCRIPTION_EVENT),
    PAUSE_ENTITLEMENT(ObjectType.BLOCKING_STATES),
    PAUSE_BILLING(ObjectType.BLOCKING_STATES),
    RESUME_ENTITLEMENT(ObjectType.BLOCKING_STATES),
    RESUME_BILLING(ObjectType.BLOCKING_STATES),
    PHASE(ObjectType.SUBSCRIPTION_EVENT),
    CHANGE(ObjectType.SUBSCRIPTION_EVENT),
    STOP_ENTITLEMENT(ObjectType.BLOCKING_STATES),
    STOP_BILLING(ObjectType.SUBSCRIPTION_EVENT),
    SERVICE_STATE_CHANGE(ObjectType.BLOCKING_STATES);

    private ObjectType objectType;

    SubscriptionEventType(ObjectType type) {
        this.objectType = type;
    }

    public ObjectType getObjectType() {
        return objectType;
    }
}