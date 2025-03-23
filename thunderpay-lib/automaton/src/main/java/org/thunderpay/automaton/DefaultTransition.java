/**
 * @file DefaultTransition.java
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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;
import org.thunderpay.xmlloader.ValidationErrors;

@XmlAccessorType(XmlAccessType.NONE)
public class DefaultTransition extends StateMachineValidatingConfig<DefaultStateMachineConfig> implements Transition, Externalizable {

    @XmlElement(name = "initialState", required = true)
    @XmlIDREF
    private DefaultState initialState;

    @XmlElement(name = "operation", required = true)
    @XmlIDREF
    private DefaultOperation operation;

    @XmlElement(name = "operationResult", required = true)
    private OperationResult operationResult;

    @XmlElement(name = "finalState", required = true)
    @XmlIDREF
    private DefaultState finalState;

    private DefaultStateMachine stateMachine;

    public DefaultTransition() {
    }

    @Override
    public String getName() {
        return initialState.getName() + "-" + operation.getName() + "-" + operationResult;
    }

    @Override
    public State getInitialState() {
        return initialState;
    }

    @Override
    public Operation getOperation() {
        return operation;
    }

    @Override
    public OperationResult getOperationResult() {
        return operationResult;
    }

    @Override
    public State getFinalState() {
        return finalState;
    }

    public DefaultStateMachine getStateMachine() {
        return stateMachine;
    }

    public void setStateMachine(final DefaultStateMachine stateMachine) {
        this.stateMachine = stateMachine;
    }

    @Override
    public ValidationErrors validate(final DefaultStateMachineConfig root, final ValidationErrors errors) {
        return errors;
    }

    public void setInitialState(final DefaultState initialState) {
        this.initialState = initialState;
    }

    public void setOperation(final DefaultOperation operation) {
        this.operation = operation;
    }

    public void setOperationResult(final OperationResult operationResult) {
        this.operationResult = operationResult;
    }

    public void setFinalState(final DefaultState finalState) {
        this.finalState = finalState;
    }

    public static Transition findTransition(final State initialState, final Operation operation, final OperationResult operationResult)
            throws MissingEntryException {
        return ((DefaultState) initialState).getStateMachine().findTransition(initialState, operation, operationResult);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final DefaultTransition that = (DefaultTransition) o;

        if (initialState != null ? !initialState.equals(that.initialState) : that.initialState != null) {
            return false;
        }
        if (operation != null ? !operation.equals(that.operation) : that.operation != null) {
            return false;
        }
        if (operationResult != that.operationResult) {
            return false;
        }
        return finalState != null ? finalState.equals(that.finalState) : that.finalState == null;
    }

    @Override
    public int hashCode() {
        int result = initialState != null ? initialState.hashCode() : 0;
        result = 31 * result + (operation != null ? operation.hashCode() : 0);
        result = 31 * result + (operationResult != null ? operationResult.hashCode() : 0);
        result = 31 * result + (finalState != null ? finalState.hashCode() : 0);
        return result;
    }

    @Override
    public void writeExternal(final ObjectOutput out) throws IOException {
        out.writeObject(initialState);
        out.writeObject(operation);
        out.writeBoolean(operationResult != null);
        if (operationResult != null) {
            out.writeUTF(operationResult.name());
        }
        out.writeObject(finalState);
    }

    @Override
    public void readExternal(final ObjectInput in) throws IOException, ClassNotFoundException {
        this.initialState = (DefaultState) in.readObject();
        this.operation = (DefaultOperation) in.readObject();
        this.operationResult = in.readBoolean() ? OperationResult.valueOf(in.readUTF()) : null;
        this.finalState = (DefaultState) in.readObject();
    }
}