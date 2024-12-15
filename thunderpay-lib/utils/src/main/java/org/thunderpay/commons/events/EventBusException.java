/**
 * @file EventBusException
 * @author Krisna Pranav
 * @brief Event Bus Exception Handlers + Contexts.
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

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
