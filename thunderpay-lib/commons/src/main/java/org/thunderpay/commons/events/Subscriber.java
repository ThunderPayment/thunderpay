/**
 * @file Subscriber.java
 * @author Krisna Pranav
 * @brief Subscriber
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.commons.events;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import javax.annotation.CheckForNull;
import org.thunderpay.commons.utils.Preconditions;
import org.thunderpay.commons.utils.annotation.VisibleForTesting;

class Subscriber {

    @VisibleForTesting
    final Object target;

    private final Method method;

    private final Executor executor;

    private final EventBus bus;

    private Subscriber(final EventBus bus, final Object target, final Method method) {
        this.bus = bus;
        this.target = Preconditions.checkNotNull(target);
        this.method = method;
        method.setAccessible(true);

        this.executor = bus.executor();
    }

    static Subscriber create(final EventBus bus, final Object listener, final Method method) {
        return isDeclaredThreadSafe(method)
                ? new Subscriber(bus, listener, method)
                : new SynchronizedSubscriber(bus, listener, method);
    }

    private static boolean isDeclaredThreadSafe(final Method method) {
        return method.getAnnotation(AllowConcurrentEvents.class) != null;
    }

    final void dispatchEvent(final Object event) {
        executor.execute(
                () -> {
                    try {
                        invokeSubscriberMethod(event);
                    } catch (final InvocationTargetException e) {
                        bus.handleSubscriberException(e.getCause(), context(event));
                    }
                });
    }

    @VisibleForTesting
    void invokeSubscriberMethod(final Object event) throws InvocationTargetException {
        try {
            method.invoke(target, Preconditions.checkNotNull(event));
        } catch (final IllegalArgumentException e) {
            throw new Error("Method rejected target/argument: " + event, e);
        } catch (final IllegalAccessException e) {
            throw new Error("Method became inaccessible: " + event, e);
        } catch (final InvocationTargetException e) {
            if (e.getCause() instanceof Error) {
                throw (Error) e.getCause();
            }
            throw e;
        }
    }

    private SubscriberExceptionContext context(final Object event) {
        return new SubscriberExceptionContext(bus, event, target, method);
    }

    @Override
    public final int hashCode() {
        return (31 + method.hashCode()) * 31 + System.identityHashCode(target);
    }

    @Override
    public final boolean equals(@CheckForNull final Object obj) {
        if (obj instanceof Subscriber) {
            final Subscriber that = (Subscriber) obj;
            return target == that.target && method.equals(that.method);
        }
        return false;
    }

    @VisibleForTesting
    static final class SynchronizedSubscriber extends Subscriber {

        private SynchronizedSubscriber(final EventBus bus, final Object target, final Method method) {
            super(bus, target, method);
        }

        @Override
        void invokeSubscriberMethod(final Object event) throws InvocationTargetException {
            synchronized (this) {
                super.invokeSubscriberMethod(event);
            }
        }
    }
}