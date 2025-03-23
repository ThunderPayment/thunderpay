/**
 * @file StateMachineConfig.java
 * @author Krisna Pranav
 * @version 1.0
 * @date 2025-03-22
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.automaton;

public interface StateMachineConfig {

    public StateMachine[] getStateMachines();

    public LinkStateMachine[] getLinkStateMachines();

    public StateMachine getStateMachineForState(final String stateName) throws MissingEntryException;
    public StateMachine getStateMachine(final String stateMachineName) throws MissingEntryException;
    public LinkStateMachine getLinkStateMachine(final String linkStateMachineName) throws MissingEntryException;
}