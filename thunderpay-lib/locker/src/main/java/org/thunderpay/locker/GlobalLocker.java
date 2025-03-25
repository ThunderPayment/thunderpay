/**
 * @file GlobalLocker.java
 * @author Krisna Pranav
 * @version 1.0
 * @date 2025-03-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.locker;

public interface GlobalLocker {

    GlobalLock lockWithNumberOfTries(String service, String lockKey, int retry) throws LockFailedException;

    boolean isFree(String service, String lockKey);
}