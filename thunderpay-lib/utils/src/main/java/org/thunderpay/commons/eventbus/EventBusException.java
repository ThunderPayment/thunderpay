/**
 * @file EventBusException.java
 * @author Krisna Pranav
 * @brief Event Bus Exception
 * @version 1.0
 * @date 2025-03-22
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.commons.eventbus;

public class EventBusException extends Exception {

    public EventBusException() {
    }

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