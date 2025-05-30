/**
 * @file DefaultStateMachine.java
 * @author Krisna Pranav
 * @version 1.0
 * @date 2025-03-22
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */


package org.thunderpay.automaton;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.stream.Stream;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlID;
import org.thunderpay.xmlloader.ValidationErrors;

@XmlAccessorType(XmlAccessType.NONE)
public class DefaultStateMachine extends StateMachineValidatingConfig<DefaultStateMachineConfig> implements StateMachine, Externalizable {

    @XmlAttribute(required = true)
    @XmlID
    private String name;

    @XmlElementWrapper(name = "states", required = true)
    @XmlElement(name = "state", required = true)
    private DefaultState[] states;

    @XmlElementWrapper(name = "transitions", required = true)
    @XmlElement(name = "transition", required = true)
    private DefaultTransition[] transitions;

    @XmlElementWrapper(name = "operations", required = true)
    @XmlElement(name = "operation", required = true)
    private DefaultOperation[] operations;

    private DefaultStateMachineConfig stateMachineConfig;

    public DefaultStateMachine() {
    }

    @Override
    public void initialize(final DefaultStateMachineConfig root) {
        stateMachineConfig = root;
        for (final DefaultState cur : states) {
            cur.initialize(root);
            cur.setStateMachine(this);
        }
        for (final DefaultTransition cur : transitions) {
            cur.initialize(root);
            cur.setStateMachine(this);
        }
        for (final DefaultOperation cur : operations) {
            cur.initialize(root);
            cur.setStateMachine(this);
        }
    }

    @Override
    public ValidationErrors validate(final DefaultStateMachineConfig root, final ValidationErrors errors) {
        validateCollection(root, errors, states);
        validateCollection(root, errors, transitions);
        validateCollection(root, errors, operations);
        return errors;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public State[] getStates() {
        return states;
    }

    @Override
    public Transition[] getTransitions() {
        return transitions;
    }

    @Override
    public Operation[] getOperations() {
        return operations;
    }

    @Override
    public State getState(final String stateName) throws MissingEntryException {
        return (State) getEntry(states, stateName);
    }

    @Override
    public Transition getTransition(final String transitionName) throws MissingEntryException {
        return (Transition) getEntry(transitions, transitionName);
    }

    @Override
    public Operation getOperation(final String operationName) throws MissingEntryException {
        return (Operation) getEntry(operations, operationName);
    }

    public boolean hasTransitionsFromStates(final String initState) {
        return Stream.of(transitions)
                .filter(input -> input != null && input.getInitialState().getName().equals(initState))
                .iterator()
                .hasNext();
    }

    public void setStates(final DefaultState[] states) {
        this.states = states;
    }

    public void setTransitions(final DefaultTransition[] transitions) {
        this.transitions = transitions;
    }

    public void setOperations(final DefaultOperation[] operations) {
        this.operations = operations;
    }

    public DefaultStateMachineConfig getStateMachineConfig() {
        return stateMachineConfig;
    }

    public void setStateMachineConfig(final DefaultStateMachineConfig stateMachineConfig) {
        this.stateMachineConfig = stateMachineConfig;
    }

    public DefaultTransition findTransition(final State initialState, final Operation operation, final OperationResult operationResult)
            throws MissingEntryException {
        try {
            return Stream.of(transitions)
                    .filter(input -> input != null &&
                            input.getInitialState().getName().equals(initialState.getName()) &&
                            input.getOperation().getName().equals(operation.getName()) &&
                            input.getOperationResult().equals(operationResult))
                    .findFirst()
                    .get();
        } catch (final NoSuchElementException e) {
            throw new MissingEntryException("Missing transition for initialState " + initialState.getName() +
                    ", operation = " + operation.getName() + ", result = " + operationResult, e);
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final DefaultStateMachine that = (DefaultStateMachine) o;

        if (name != null ? !name.equals(that.name) : that.name != null) {
            return false;
        }
        if (!Arrays.equals(states, that.states)) {
            return false;
        }
        if (!Arrays.equals(transitions, that.transitions)) {
            return false;
        }
        return Arrays.equals(operations, that.operations);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + Arrays.hashCode(states);
        result = 31 * result + Arrays.hashCode(transitions);
        result = 31 * result + Arrays.hashCode(operations);
        return result;
    }

    @Override
    public void writeExternal(final ObjectOutput out) throws IOException {
        out.writeUTF(name);
        out.writeObject(states);
        out.writeObject(transitions);
        out.writeObject(operations);
    }

    @Override
    public void readExternal(final ObjectInput in) throws IOException, ClassNotFoundException {
        this.name = in.readUTF();
        this.states = (DefaultState[]) in.readObject();
        this.transitions = (DefaultTransition[]) in.readObject();
        this.operations = (DefaultOperation[]) in.readObject();
    }
}