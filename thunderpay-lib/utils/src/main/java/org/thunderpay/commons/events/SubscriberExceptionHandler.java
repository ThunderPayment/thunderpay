/**
 * @file SubscriberExceptionHandler.java
 * @author Nuke Williams
 * @brief Subscriber Exception Handler
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, NukeWiliams
 *
 */

package org.thunderpay.commons.events;

public interface SubscriberExceptionHandler {
    void handleException(Throwable exception, SubscriberExceptionContext context);
}