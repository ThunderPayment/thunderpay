/**
 * @file MissingEntryException.java
 * @author Krisna Pranav
 * @version 1.0
 * @date 2025-03-22
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.automaton;

public class MissingEntryException extends Exception {

    public MissingEntryException() {
    }

    public MissingEntryException(final String message) {
        super(message);
    }

    public MissingEntryException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public MissingEntryException(final Throwable cause) {
        super(cause);
    }

}