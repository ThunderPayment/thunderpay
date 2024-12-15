/**
 * @file DirectExecutor.java
 * @author Krisna Pranav
 * @brief Direct Executor.
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.commons.utils.concurrent;

import java.util.concurrent.Executor;

public enum DirectExecutor implements Executor  {
    INSTANCE;

    @Override
    public void execute(final Runnable command) {
        command.run();
    }
}
