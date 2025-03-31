/**
 * @file MemoryGlobalLocker.java
 * @author Krisna Pranav
 * @version 1.0
 * @date 2025-03-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.locker.memory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.thunderpay.locker.GlobalLock;
import org.thunderpay.locker.GlobalLocker;
import org.thunderpay.locker.GlobalLockerBase;

public class MemoryGlobalLocker extends GlobalLockerBase implements GlobalLocker {

    private final Map<String, AtomicBoolean> locks = new ConcurrentHashMap<String, AtomicBoolean>();

    public MemoryGlobalLocker() {
        super(DEFAULT_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
    }

    @Override
    public synchronized boolean isFree(final String service, final String lockKey) {
        final String lockName = getLockName(service, lockKey);
        return isFree(lockName);
    }

    private synchronized Boolean isFree(final String lockName) {
        final AtomicBoolean lock = locks.get(lockName);
        return lock == null || !lock.get();
    }

    @Override
    protected synchronized GlobalLock doLock(final String lockName) {
        if (!isFree(lockName)) {
            return null;
        }

        if (locks.get(lockName) == null) {
            locks.put(lockName, new AtomicBoolean(true));
        } else {
            locks.get(lockName).set(true);
        }

        final GlobalLock lock = new GlobalLock() {
            @Override
            public void release() {
                if (lockTable.releaseLock(lockName)) {
                    locks.get(lockName).set(false);
                }
            }
        };

        lockTable.createLock(lockName, lock);

        return lock;
    }

    @Override
    protected String getLockName(final String service, final String lockKey) {
        return service + "-" + lockKey;
    }
}