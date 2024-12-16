/**
 * @file DefaultCatchableSubscriberExceptionsHandler.java
 * @author Krisna Pranav
 * @brief Default Catchable Subscriber Exceptions Handler
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.commons.events;

import java.lang.reflect.InvocationTargetException;

class DefaultCatchableSubscriberExceptionsHandler implements CatchableSubscriberExceptionHandler {
    private final ThreadLocal<Exception> lastException = new ThreadLocal<>() {};

    private final SubscriberExceptionHandler loggerHandler = LoggingHandler.INSTANCE;

    @Override
    public void handleException(final Throwable exception, final SubscriberExceptionContext context) {
        if (lastException.get() == null) {
            lastException.set(new InvocationTargetException(exception));
        }

        loggerHandler.handleException(exception, context);
    }

    @Override
    public Exception caughtException() {
        final Exception exception = lastException.get();
        reset();
        return exception;
    }

    @Override
    public void reset() {
        lastException.set(null);
    }
}