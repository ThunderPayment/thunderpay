package org.thunderpay.commons.events;

public class EventBusException extends Exception {
    public EventBusException() {}

    public EventBusException(final String message) {
        super(message);
    }

    public EventBusException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public EventBusException(final Throwable cause) {
        super(cause);
    }
}
