/**
 * @file ReentrantLock.java
 * @author Krisna Pranav
 * @version 1.0
 * @date 2025-03-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.locker;

import org.thunderpay.commons.request.Request;
import org.thunderpay.commons.request.RequestData;
import java.util.HashMap;
import java.util.Map;

public class ReentrantLock {

    private final Map<String, LockHolder> lockTable;

    public ReentrantLock() {
        this.lockTable = new HashMap<String, LockHolder>();
    }

    public enum ReentrantLockState {
        FREE,
        HELD_OWNER,
        HELD_NOT_OWNER
    }

    public static class TryAcquireLockState {

        private final ReentrantLockState lockState;
        private final GlobalLock originalLock;

        public TryAcquireLockState(final ReentrantLockState lockState) {
            this(lockState, null);
        }

        public TryAcquireLockState(final ReentrantLockState lockState, final GlobalLock originalLock) {
            this.lockState = lockState;
            this.originalLock = originalLock;
        }

        public ReentrantLockState getLockState() {
            return lockState;
        }

        public GlobalLock getOriginalLock() {
            return originalLock;
        }
    }

    /**
     * Atomically increment the refCount lock if we are already the owner of that lock.
     *
     * @param lockName
     * @return the ReentrantLockState: lock is FREE, or we already hold it (and incremented the refCount) or it held by somebody else
     */
    public TryAcquireLockState tryAcquireLockForExistingOwner(final String lockName) {

        synchronized (lockTable) {
            final LockHolder lockHolder = lockTable.get(lockName);
            if (lockHolder == null) {
                return new TryAcquireLockState(ReentrantLockState.FREE);
            }

            final String maybeNullRequestId = getRequestId();
            if (maybeNullRequestId == null || !lockHolder.getRequestId().equals(maybeNullRequestId)) {
                return new TryAcquireLockState(ReentrantLockState.HELD_NOT_OWNER);
            } else {
                // Increment value before we return while we hold the lockTable lock.
                lockHolder.increment();
                return new TryAcquireLockState(ReentrantLockState.HELD_OWNER, lockHolder.getOriginalLock());
            }
        }
    }


    /**
     * Create a new LockHolder. This is done *after* the distributed lock was acquired.
     *
     * @param lockName
     */
    public void createLock(final String lockName, final GlobalLock originalLock) {

        final String requestId = getRequestId();
        if (requestId == null) {
            return;
        }

        synchronized (lockTable) {
            LockHolder lockHolder = lockTable.get(lockName);
            if (lockHolder != null) {
                throw new IllegalStateException(String.format("ReentrantLock createLock %s : lock already current request = %s, owner request = %s", lockName, requestId, lockHolder.getRequestId()));
            }

            lockHolder = new LockHolder(requestId, originalLock);
            lockTable.put(lockName, lockHolder);
            lockHolder.increment();
        }
    }


    /**
     * Release a lock. This is always called when the resources are feed
     *
     * @param lockName
     * @return true if nobody still holds the reentrant lock (and thefeore distributed lock can be freed)
     */
    public boolean releaseLock(final String lockName) {

        // In case there no requestId set, this was not a 'reentrant' lock, so nothing to do but we need to return true
        // so distributed lock can be released
        //
        final String requestId = getRequestId();
        if (requestId == null) {
            return true;
        }

        synchronized (lockTable) {
            final LockHolder lockHolder = lockTable.get(lockName);
            if (lockHolder == null) {
                throw new IllegalStateException(String.format("ReentrantLock releaseLock %s : cannot find lock in the table, current request = %s", lockName, requestId));
            }

            if (!lockHolder.getRequestId().equals(requestId)) {
                throw new IllegalStateException(String.format("ReentrantLock releaseLock %s : current request = %s, owner request = %s", lockName, requestId, lockHolder.getRequestId()));
            }
            final boolean free = lockHolder.decrement();
            if (free) {
                lockTable.remove(lockName);
            }
            return free;
        }
    }


    private String getRequestId() {
        final RequestData requestData = Request.getPerThreadRequestData();
        return requestData != null ? requestData.getRequestId() : null;
    }

    private static class LockHolder {

        private final String requestId;
        private final GlobalLock originalLock;

        private int refCount;


        public LockHolder(final String requestId, final GlobalLock originalLock) {
            this.requestId = requestId;
            this.originalLock = originalLock;
            this.refCount = 0;
        }

        public void increment() {
            refCount++;
        }


        public boolean decrement() {
            return --refCount == 0;
        }

        public String getRequestId() {
            return requestId;
        }

        public GlobalLock getOriginalLock() {
            return originalLock;
        }
    }
}