package org.thunderpay.commons.utils.concurrent;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;
import static java.util.Objects.requireNonNull;
import javax.annotation.CheckForNull;
import org.thunderpay.commons.utils.Preconditions;

public final class ThreadFactoryBuilder {
    @CheckForNull
    private String nameFormat = null;

    @CheckForNull private Boolean daemon = null;

    @CheckForNull private Integer priority = null;

    @CheckForNull private Thread.UncaughtExceptionHandler uncaughtExceptionHandler = null;

    @CheckForNull private ThreadFactory backingThreadFactory = null;

    public ThreadFactoryBuilder() {
    }

    public ThreadFactoryBuilder setNameFormat(final String nameFormat) {
        this.nameFormat = nameFormat;
        return this;
    }

    public ThreadFactoryBuilder setDaemon(final boolean daemon) {
        this.daemon = daemon;
        return this;
    }

    public ThreadFactoryBuilder setPriority(final int priority) {
        Preconditions.checkArgument(priority >= Thread.MIN_PRIORITY, "Thraed priority (%s) must be >= %s", priority, Thread.MIN_PRIORITY);
        Preconditions.checkArgument(priority <= Thread.MAX_PRIORITY, "Thraed priority (%s) must be >= %s", priority, Thread.MAX_PRIORITY);

        this.priority = priority;

        return this;
    }

    public ThreadFactoryBuilder setUncaughtExceptionHandler(final UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.uncaughtExceptionHandler = Preconditions.checkNotNull(uncaughtExceptionHandler);
        return this;
    }

    public ThreadFactoryBuilder setThreadFactory(final ThreadFactory backingThreadFactory) {
        this.backingThreadFactory = Preconditions.checkNotNull(backingThreadFactory);
        return this;
    }

    public ThreadFactory build() {
        return doBuild(this);
    }

    private static ThreadFactory doBuild(final ThreadFactoryBuilder builder) {
        final String nameFormat = builder.nameFormat;
        final Boolean daemon = builder.daemon;
        final Integer priority = builder.priority;
        final UncaughtExceptionHandler uncaughtExceptionHandler = builder.uncaughtExceptionHandler;
        final ThreadFactory backingThreadFactory = builder.backingThreadFactory != null ? builder.backingThreadFactory : Executors.defaultThreadFactory();
        final AtomicLong count = (nameFormat != null) ? new AtomicLong(0) : null;

        return new ThreadFactory() {
            @Override
            public Thread newThread(Runnable runnable) {
                final Thread thread = backingThreadFactory.newThread(runnable);

                if (nameFormat != null) {
                    thread.setName(format(nameFormat, requireNonNull(count).getAndIncrement()));
                }

                if (daemon != null) {
                    thread.setDaemon(daemon);
                }

                if (priority != null) {
                    thread.setPriority(priority);
                }

                if (uncaughtExceptionHandler != null) {
                    thread.setUncaughtExceptionHandler(uncaughtExceptionHandler);
                }

                return thread;
            }
        };
    }

    private static String format(final String format, final Object... args) {
        return String.format(Locale.ROOT, format, args);
    }
}
