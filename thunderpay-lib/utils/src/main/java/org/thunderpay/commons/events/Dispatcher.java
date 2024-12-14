package org.thunderpay.commons.events;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;
import org.thunderpay.commons.utils.Preconditions

abstract class Dispatcher {
    static Dispatcher perThreadDispatchQueue() {
        return new PerThreadQueuedDispatcher();
    }

    static Dispatcher immediate() {
        return ImmediateDispatcher.INSTANCE;
    }

    abstract void dispatch(Object event, Iterator<Subscriber> subscribers);

    static final class PerThreadQueuedDispatcher extends Dispatcher {
        private final ThreadLocal<Queue<Event>> queue = ThreadLocal.withInitial(ArrayDeque::new);
        private final ThreadLocal<Boolean> dispatching = ThreadLocal.withInitial(() -> false);

        @Override
        void dispatch(final Object event, final Iterator<Subscriber> subscribers) {
            Preconditions.checkNotNull(event);
            Preconditions.checkNotNull(subscribers);
            final Queue<Event> queueForThread = queue.get();
            queueForThread.offer(new Event(event, subscribers));

            if (!dispatching.get()) {
                dispatching.set(true);
                try {
                    Event nextEvent;
                    while ((nextEvent = queueForThread.poll()) != null) {
                        while (nextEvent.subscribers.hasNext()) {
                            nextEvent.subscribers.next().dispatchEvent(nextEvent.event);
                        }
                    }
                } finally {
                    dispatching.remove();
                    queue.remove();
                }
            }
        }

        private static final class Event {

            private final Object event;
            private final Iterator<Subscriber> subscribers;

            private Event(final Object event, final Iterator<Subscriber> subscribers) {
                this.event = event;
                this.subscribers = subscribers;
            }
        }
    }

    static final class ImmediateDispatcher extends Dispatcher {

        private static final ImmediateDispatcher INSTANCE = new ImmediateDispatcher();

        @Override
        void dispatch(final Object event, final Iterator<Subscriber> subscribers) {
            Preconditions.checkNotNull(event);
            while (subscribers.hasNext()) {
                subscribers.next().dispatchEvent(event);
            }
        }
    }
}