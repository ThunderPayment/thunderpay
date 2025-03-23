/**
 * @file DefaultStateMachineConfigDOTGenerator.java
 * @author Krisna Pranav
 * @version 1.0
 * @date 2025-03-22
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.automaton.dot;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.thunderpay.automaton.*;
import org.thunderpay.automaton.graph.Helpers;

public class DefaultStateMachineConfigDOTGenerator {

    private final String name;
    private final StateMachineConfig config;

    private final Map<String, Integer> statesNodeIds = new HashMap<String, Integer>();

    private DOTBuilder dot;

    public DefaultStateMachineConfigDOTGenerator(final String name, final StateMachineConfig config) {
        this.name = name;
        this.config = config;
    }

    public DOTBuilder getDot() {
        return dot;
    }

    public void build() {
        dot = new DOTBuilder(name);
        dot.open();

        for (final StateMachine stateMachine : config.getStateMachines()) {
            final Set<String> initialStates = Helpers.findInitialStates(stateMachine);
            final Set<String> finalStates = Helpers.findFinalStates(stateMachine);

            dot.openCluster(stateMachine.getName());

            for (final State state : stateMachine.getStates()) {
                drawState(state, initialStates, finalStates);
            }

            for (final Transition transition : stateMachine.getTransitions()) {
                drawTransition(transition);
            }

            dot.closeCluster();
        }

        for (final LinkStateMachine linkStateMachine : config.getLinkStateMachines()) {
            drawLinkStateMachine(linkStateMachine);
        }

        dot.close();
    }

    @Override
    public String toString() {
        if (dot == null) {
            build();
        }
        return dot.toString();
    }

    private void drawState(final State state, final Collection<String> initialStates, final Collection<String> finalStates) {
        final Map<String, String> attributes = new HashMap<String, String>();
        if (initialStates.contains(state.getName()) || finalStates.contains(state.getName())) {
            attributes.put("color", "grey");
            attributes.put("style", "filled");
        }

        final int nodeId = dot.addNode(state.getName(), attributes);
        statesNodeIds.put(state.getName(), nodeId);
    }

    private void drawTransition(final Transition transition) {
        final Integer fromNodeId = statesNodeIds.get(transition.getInitialState().getName());
        final Integer toNodeId = statesNodeIds.get(transition.getFinalState().getName());

        final String color;
        switch (transition.getOperationResult()) {
            case FAILURE:
            case EXCEPTION:
                color = "red";
                break;
            case SUCCESS:
                color = "green";
                break;
            default:
                color = "black";
                break;
        }

        final String label = String.format("<%s<SUB>|%s</SUB>>", transition.getOperation().getName(), transition.getOperationResult().name().charAt(0));
        dot.addPath(fromNodeId, toNodeId, Map.<String, String>of("label", label, "color", color));
    }

    private void drawLinkStateMachine(final LinkStateMachine linkStateMachine) {
        final Integer fromNodeId = statesNodeIds.get(linkStateMachine.getInitialState().getName());
        final Integer toNodeId = statesNodeIds.get(linkStateMachine.getFinalState().getName());
        dot.addPath(fromNodeId, toNodeId, Map.<String, String>of("style", "dotted"));
    }
}