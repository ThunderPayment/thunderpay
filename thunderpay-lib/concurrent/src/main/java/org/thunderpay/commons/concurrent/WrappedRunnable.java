/**
 * @file WrappedRunnable.java
 * @author Krisna Pranav
 * @brief Wrapped Runnable
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.commons.concurrent;

import org.slf4j.Logger;

class WrappedRunnable implements Runnable {

    private final Logger log;
    private final Runnable runnable;

    private volatile Throwable exception;

    private WrappedRunnable(final Logger log, final Runnable runnable) {
        this.log = log;
        this.runnable = runnable;
    }

    public static WrappedRunnable wrap(final Logger log, final Runnable runnable) {
        return runnable instanceof WrappedRunnable ? (WrappedRunnable) runnable : new WrappedRunnable(log, runnable);
    }

    Throwable getException() {
        return exception;
    }

    @Override
    public void run() {
        final Thread currentThread = Thread.currentThread();

        try {
            runnable.run();
        } catch (final Throwable e) {
            log.error(currentThread + " ended with an exception", e);
            exception = e;
        }

        log.debug("{} finished executing", currentThread);
    }
}