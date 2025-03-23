/**
 * @file Operation.java
 * @author Krisna Pranav
 * @version 1.0
 * @date 2025-03-22
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.automaton;

public interface Operation extends StateMachineEntry {

    public StateMachine getStateMachine();

    public OperationResult run(OperationCallback cb) throws OperationException;

    public interface OperationCallback {
        public OperationResult doOperationCallback() throws OperationException;
    }
}
