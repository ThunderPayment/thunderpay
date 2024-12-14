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