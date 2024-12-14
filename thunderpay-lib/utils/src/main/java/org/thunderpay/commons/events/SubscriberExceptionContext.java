/**
 * @file SubscriberExceptionContext.java
 * @author Nuke Williams
 * @brief Subscriber Exception Context
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, NukeWiliams
 *
 */

package org.thunderpay.commons.events;

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