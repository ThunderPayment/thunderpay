/**
 * @file DirectExecutor.java
 * @author Krisna Pranav
 * @version 1.0
 * @date 2025-03-22
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.commons.utils.concurrent;

import java.util.concurrent.Executor;

public enum DirectExecutor implements Executor {

    INSTANCE;

    @Override
    public void execute(final Runnable command) {
        command.run();
    }
}
