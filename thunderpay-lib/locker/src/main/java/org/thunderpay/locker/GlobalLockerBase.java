/**
 * @file GlobalLockerBase.java
 * @author Krisna Pranav
 * @version 1.0
 * @date 2025-03-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.locker;

import java.util.concurrent.TimeUnit;

import org.thunderpay.locker.ReentrantLock.TryAcquireLockState;
import org.thunderpay.commons.profiling.Profiling;
import org.thunderpay.commons.profiling.Profiling.WithProfilingCallback;
import org.thunderpay.commons.profiling.ProfilingFeature.ProfilingFeatureType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class GlobalLockerBase implements GlobalLocker {

    protected static final long DEFAULT_TIMEOUT_MILLIS = 100L;

    protected static final Logger logger = LoggerFactory.getLogger(GlobalLockerBase.class);

    protected final long timeout;
    protected final TimeUnit timeUnit;
    protected final ReentrantLock lockTable;

    private final Profiling<GlobalLock, LockFailedException> prof;

    public GlobalLockerBase(final long timeout, final TimeUnit timeUnit) {
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        this.lockTable = new ReentrantLock();
        this.prof = new Profiling<GlobalLock, LockFailedException>();
    }

    @Override
    public GlobalLock lockWithNumberOfTries(final String service, final String lockKey, final int retry) throws LockFailedException {
        return prof.executeWithProfiling(ProfilingFeatureType.GLOCK, "lock", new WithProfilingCallback<GlobalLock, LockFailedException>() {
            @Override
            public GlobalLock execute() throws LockFailedException {
                final String lockName = getLockName(service, lockKey);
                int tries_left = retry;
                while (tries_left-- > 0) {
                    final GlobalLock lock = lock(lockName);
                    if (lock != null) {
                        return lock;
                    }
                    if (tries_left > 0) {
                        sleep();
                    }
                }

                logger.warn(String.format("Failed to acquire lock %s for service %s after %s retries", lockKey, service, retry));
                throw new LockFailedException();
            }
        });
    }

    protected GlobalLock lock(final String lockName) {
        final TryAcquireLockState lockState = lockTable.tryAcquireLockForExistingOwner(lockName);
        if (lockState.getLockState() == ReentrantLock.ReentrantLockState.HELD_OWNER) {
            return lockState.getOriginalLock();
        }

        if (lockState.getLockState() == ReentrantLock.ReentrantLockState.HELD_NOT_OWNER) {
            // In that case, we need to respect the provided timeout value
            try {
                Thread.sleep(TimeUnit.MILLISECONDS.convert(timeout, timeUnit));
            } catch (final InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.warn("lock got interrupted", e);
            }
            return null;
        }

        return doLock(lockName);
    }

    protected abstract GlobalLock doLock(final String lockName);

    protected abstract String getLockName(final String service, final String lockKey);

    private void sleep() {
        try {
            Thread.sleep(TimeUnit.MILLISECONDS.convert(timeout, timeUnit));
        } catch (final InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.warn("GlobalLockerBase got interrupted", e);
        }
    }
}