/**
 * @file Helpers.java
 * @author Krisna Pranav
 * @version 1.0
 * @date 2025-03-22
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.automaton.graph;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.thunderpay.automaton.State;
import org.thunderpay.automaton.StateMachine;
import org.thunderpay.automaton.Transition;

public class Helpers {
    public static Set<String> findInitialStates(final StateMachine stateMachine) {
        return findEdgeStates(stateMachine, true);
    }

    public static Set<String> findFinalStates(final StateMachine stateMachine) {
        return findEdgeStates(stateMachine, false);
    }

    private static Set<String> findEdgeStates(final StateMachine stateMachine, final boolean initial) {
        final Set<String> edgeStates = new HashSet<String>();
        final Collection<String> complementStates = new HashSet<String>();

        for (final Transition transition : stateMachine.getTransitions()) {
            final String stateName = initial ? transition.getFinalState().getName() : transition.getInitialState().getName();
            complementStates.add(stateName);
        }

        for (final State state : stateMachine.getStates()) {
            if (!complementStates.contains(state.getName())) {
                edgeStates.add(state.getName());
            }
        }

        return edgeStates;
    }
}