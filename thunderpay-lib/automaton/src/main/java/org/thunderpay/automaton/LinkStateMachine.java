/**
 * @file LinkStateMachine.java
 * @author Krisna Pranav
 * @version 1.0
 * @date 2025-03-22
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.automaton;

public interface LinkStateMachine extends StateMachineEntry{

    public StateMachine getInitialStateMachine();

    public State getInitialState();

    public StateMachine getFinalStateMachine();

    public State getFinalState();
}