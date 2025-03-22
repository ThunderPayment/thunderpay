/**
 * @file CatchableSubscriberExceptionHandler.java
 * @author Krisna Pranav
 * @brief Catchable Subscriber Exception Handler
 * @version 1.0
 * @date 2025-03-22
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.commons.eventbus;

public interface CatchableSubscriberExceptionHandler extends SubscriberExceptionHandler {

    Exception caughtException();

    void reset();
}
