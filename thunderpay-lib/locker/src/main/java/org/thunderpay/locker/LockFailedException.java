/**
 * @file LockFailedException.java
 * @author Krisna Pranav
 * @version 1.0
 * @date 2025-03-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.locker;

public class LockFailedException extends Exception {

    public LockFailedException() {
        super();
    }

    public LockFailedException(final Throwable t) {
        super(t);
    }
}