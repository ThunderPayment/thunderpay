/**
 * @file WrappedRunnableFuture
 * @author Krisna Pranav
 * @brief Wrapped Runnable Future
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.commons.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


public class WrappedRunnableFuture<V> implements Future<V> {
    private final WrappedRunnable runnable;
    private final Future<V> delegate;

    private WrappedRunnableFuture(final WrappedRunnable runnable, final Future<V> delegate) {
        this.runnable = runnable;
        this.delegate = delegate;
    }

    public static<V> Future<V> wrap(final WrappedRunnable runnable, final Future<V> delegate) {
        return new WrappedRunnableFuture<V>(runnable, delegate);
    }
}