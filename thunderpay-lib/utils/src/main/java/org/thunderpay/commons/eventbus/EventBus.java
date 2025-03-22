/**
 * @file EventBus.java
 * @author Krisna Pranav
 * @brief Event Bus
 * @version 1.0
 * @date 2025-03-22
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.commons.eventbus;

import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.thunderpay.commons.eventbus.Dispatcher.ImmediateDispatcher;
import org.thunderpay.commons.utils.Preconditions;
import org.thunderpay.commons.utils.concurrent.DirectExecutor;

public class EventBus {

    private static final Logger logger = Logger.getLogger(EventBus.class.getName());

    private final String identifier;
    private final Executor executor;
    private final SubscriberExceptionHandler exceptionHandler;

    private final SubscriberRegistry subscribers = new SubscriberRegistry(this);
    private final Dispatcher dispatcher;

    public EventBus() {
        this("default");
    }

    public EventBus(final String identifier) {
        this(identifier, DirectExecutor.INSTANCE, Dispatcher.immediate(), new DefaultCatchableSubscriberExceptionsHandler());
    }

    public EventBus(final SubscriberExceptionHandler exceptionHandler) {
        this("default", DirectExecutor.INSTANCE, Dispatcher.immediate(), exceptionHandler);
    }

    public EventBus(final String identifier, final Executor executor, final Dispatcher dispatcher, final SubscriberExceptionHandler exceptionHandler) {
        this.identifier = Preconditions.checkNotNull(identifier);
        this.executor = Preconditions.checkNotNull(executor);
        this.dispatcher = Preconditions.checkNotNull(dispatcher);
        this.exceptionHandler = Preconditions.checkNotNull(exceptionHandler);
    }

    public final String identifier() {
        return identifier;
    }

    final Executor executor() {
        return executor;
    }

    void handleSubscriberException(final Throwable e, final SubscriberExceptionContext context) {
        Preconditions.checkNotNull(e);
        Preconditions.checkNotNull(context);
        try {
            exceptionHandler.handleException(e, context);
        } catch (final Throwable e2) {
            logger.log(
                    Level.SEVERE,
                    String.format(Locale.ROOT, "Exception %s thrown while handling exception: %s", e2, e),
                    e2);
        }
    }

    public void register(final Object object) {
        subscribers.register(object);
    }

    public void unregister(final Object object) {
        subscribers.unregister(object);
    }

    public void post(final Object event) {
        final Iterator<Subscriber> eventSubscribers = subscribers.getSubscribers(event);
        if (eventSubscribers.hasNext()) {
            dispatcher.dispatch(event, eventSubscribers);
        } else if (!(event instanceof DeadEvent)) {
            post(new DeadEvent(this, event));
        }
    }

    public void postWithException(final Object event) throws EventBusException {
        Preconditions.checkState(exceptionHandler instanceof CatchableSubscriberExceptionHandler, "exceptionHandler should be instance of CatchableSubscriberExceptionHandler");
        Preconditions.checkState(executor instanceof  DirectExecutor, "executor should be instance of DirectExecutor");
        Preconditions.checkState(dispatcher instanceof ImmediateDispatcher, "dispatcher should be instance of ImmediateDispatcher");

        final CatchableSubscriberExceptionHandler catchableExceptionHandler = (CatchableSubscriberExceptionHandler) exceptionHandler;
        final Iterator<Subscriber> eventSubscribers = subscribers.getSubscribers(event);
        if (eventSubscribers.hasNext()) {
            catchableExceptionHandler.reset();

            RuntimeException guavaException = null;
            final Exception subscriberException;
            try {
                dispatcher.dispatch(event, eventSubscribers);
            } catch (final RuntimeException e) {
                guavaException = e;
            } finally {
                subscriberException = catchableExceptionHandler.caughtException();
            }

            if (guavaException != null) {
                throw guavaException;
            }
            if (subscriberException != null) {
                throw new EventBusException(subscriberException);
            }
        } else if (!(event instanceof DeadEvent)) {
            post(new DeadEvent(this, event));
        }
    }

    @Override
    public String toString() {
        return "EventBus {" +
                "identifier='" + identifier + '\'' +
                '}';
    }
}