/**
 * @file OperationException.java
 * @author Krisna Pranav
 * @version 1.0
 * @date 2025-03-22
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.automaton;

public class OperationException extends Exception {

    private final OperationResult operationResult;

    public OperationException() {
        this(null, OperationResult.EXCEPTION);
    }

    public OperationException(final Throwable cause) {
        this(cause, OperationResult.EXCEPTION);
    }

    public OperationException(final Throwable cause, final OperationResult operationResult) {
        super(cause);
        this.operationResult = operationResult;
    }

    public OperationResult getOperationResult() {
        return operationResult;
    }
}