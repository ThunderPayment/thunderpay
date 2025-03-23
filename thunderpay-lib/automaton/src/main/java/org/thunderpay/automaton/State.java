/**
 * @file State.java
 * @author Krisna Pranav
 * @version 1.0
 * @date 2025-03-22
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.automaton;

public interface State extends StateMachineEntry {

    public interface EnteringStateCallback {
        public void enteringState(final State newState, final Operation.OperationCallback operationCallback, final OperationResult operationResult, final LeavingStateCallback leavingStateCallback);
    }

    public interface LeavingStateCallback {
        public void leavingState(final State oldState) throws OperationException;
    }

    public void runOperation(final Operation operation, final Operation.OperationCallback operationCallback,  final EnteringStateCallback enteringStateCallback, final LeavingStateCallback leavingStateCallback)
            throws MissingEntryException, OperationException;

}