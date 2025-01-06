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

    public static <V> Future<V> wrap(final WrappedRunnable runnable, final Future<V> delegate) {
        return new WrappedRunnableFuture<V>(runnable, delegate);
    }

    @Override
    public boolean cancel(final boolean mayInterruptIfRunning) {
        return delegate.cancel(mayInterruptIfRunning);
    }

    @Override
    public boolean isCancelled() {
        return delegate.isCancelled();
    }

    @Override
    public boolean isDone() {
        return delegate.isDone();
    }

    @Override
    public V get() throws InterruptedException, ExecutionException {
        final V result = delegate.get();

        checkForException();

        return result;
    }

    @Override
    public V get(final long timeout, final TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        final V result = delegate.get(timeout, unit);

        checkForException();

        return result;
    }

    private void checkForException() throws InterruptedException, ExecutionException {
        final Throwable exception = runnable.getException();

        if (exception != null) {
            if (exception instanceof InterruptedException) {
                throw (InterruptedException) exception;
            }

            if (exception instanceof ExecutionException) {
                throw (ExecutionException) exception;
            }

            throw new ExecutionException(exception);
        }
    }
}