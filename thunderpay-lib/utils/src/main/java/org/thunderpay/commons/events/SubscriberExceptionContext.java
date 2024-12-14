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

public class SubscriberExceptionContext {
    private final EventBus eventBus;
    private final Object event;
    private final Object subscriber;
    private final Method subscriberMethod;
}