/**
 * @file SubscriberExceptionHandler.java
 * @author Krisna Pranav
 * @brief Subscriber Exception Handler
 * @version 1.0
 * @date 2025-03-22
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.commons.eventbus;

public interface SubscriberExceptionHandler {
    void handleException(Throwable exception, SubscriberExceptionContext context);
}