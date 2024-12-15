/**
 * @file LoggingHandler.java
 * @author Krisna Pranav
 * @brief Log Handler
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.commons.events;

import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

class LoggingHandler implements SubscriberExceptionHandler {

    static final SubscriberExceptionHandler INSTANCE = new LoggingHandler();

    private static Logger logger(final SubscriberExceptionContext context) {
        return Logger.getLogger(EventBus.class.getName() + "." + context.getEventBus().identifier());
    }

    private static String message(final SubscriberExceptionContext context) {
        final Method method = context.getSubscriberMethod();
        return "Exception thrown by subscriber method "
               + method.getName()
               + '('
               + method.getParameterTypes()[0].getName()
               + ')'
               + " on subscriber "
               + context.getSubscriber()
               + " when dispatching event: "
               + context.getEvent();
    }

    @Override
    public void handleException(final Throwable exception, final SubscriberExceptionContext context) {
        final Logger logger = logger(context);
        if (logger.isLoggable(Level.SEVERE)) {
            logger.log(Level.SEVERE, message(context), exception);
        }
    }
}