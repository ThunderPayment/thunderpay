/**
 * @file Transition.java
 * @author Krisna Pranav
 * @version 1.0
 * @date 2025-03-22
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.automaton;

public interface Transition extends StateMachineEntry {

    public State getInitialState();

    public Operation getOperation();

    public OperationResult getOperationResult();

    public State getFinalState();
}