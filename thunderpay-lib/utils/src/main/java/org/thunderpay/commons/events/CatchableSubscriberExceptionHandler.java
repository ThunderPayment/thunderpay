/**
 * @file CatchableSubscriberExceptionHandler.java
 * @author Nuke Williams
 * @brief Catchable Subscriber Exception Handler
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, NukeWiliams
 *
 */

package org.thunderpay.commons.events;

public interface CatchableSubscriberExceptionHandler extends SubscriberExceptionHandler {
    // caughtExcetion return Exception
    Exception caughtException();

    // reset
    void reset();
}