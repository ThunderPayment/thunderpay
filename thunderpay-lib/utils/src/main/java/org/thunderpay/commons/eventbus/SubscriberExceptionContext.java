/**
 * @file SubscriberExceptionContext.java
 * @author Krisna Pranav
 * @brief Subscriber Exception Context
 * @version 1.0
 * @date 2025-03-22
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.commons.eventbus;

import java.lang.reflect.Method;
import org.thunderpay.commons.utils.Preconditions;

public class SubscriberExceptionContext {

    private final EventBus eventBus;
    private final Object event;
    private final Object subscriber;
    private final Method subscriberMethod;

    SubscriberExceptionContext(final EventBus eventBus, final Object event, final Object subscriber, final Method subscriberMethod) {
        this.eventBus = Preconditions.checkNotNull(eventBus);
        this.event = Preconditions.checkNotNull(event);
        this.subscriber = Preconditions.checkNotNull(subscriber);
        this.subscriberMethod = Preconditions.checkNotNull(subscriberMethod);
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    public Object getEvent() {
        return event;
    }

    public Object getSubscriber() {
        return subscriber;
    }

    Method getSubscriberMethod() {
        return subscriberMethod;
    }
}