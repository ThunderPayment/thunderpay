/**
 * @file StateMachine.java
 * @author Krisna Pranav
 * @version 1.0
 * @date 2025-03-22
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.automaton;

public interface StateMachine extends StateMachineEntry {

    public State [] getStates();
    public Transition [] getTransitions();
    public Operation [] getOperations();

    public State getState(final String stateName) throws MissingEntryException;
    public Transition getTransition(final String transitionName) throws MissingEntryException;
    public Operation getOperation(final String operationName) throws MissingEntryException;
}