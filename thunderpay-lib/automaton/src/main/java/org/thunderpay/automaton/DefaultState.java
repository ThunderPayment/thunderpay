/**
 * @file DefaultState.java
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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import org.thunderpay.xmlloader.ValidationErrors;

@XmlAccessorType(XmlAccessType.NONE)
public class DefaultState extends StateMachineValidatingConfig<DefaultStateMachineConfig> implements State, Externalizable {

    @XmlAttribute(required = true)
    @XmlID
    private String name;

    private DefaultStateMachine stateMachine;

    public DefaultState() {
    }

    @Override
    public ValidationErrors validate(final DefaultStateMachineConfig root, final ValidationErrors errors) {
        return errors;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void runOperation(final Operation operation, final Operation.OperationCallback operationCallback, final EnteringStateCallback enteringStateCallback, final LeavingStateCallback leavingStateCallback)
            throws MissingEntryException, OperationException {

        OperationException rethrowableException = null;
        OperationResult result = OperationResult.EXCEPTION;
        Transition transition = null;
        State initialState = this;
        try {
            final StateMachine destStateMachine = operation.getStateMachine();

            try {
                final LinkStateMachine linkStateMachine = DefaultLinkStateMachine.findLinkStateMachine(this.getStateMachine(), this, destStateMachine);
                initialState = linkStateMachine.getFinalState();
            } catch (final MissingEntryException e) {
                initialState = this;
            }

            if (!((DefaultState) initialState).getStateMachine().hasTransitionsFromStates(initialState.getName())) {
                throw new MissingEntryException("No transition exists from state " + initialState.getName());
            }

            boolean hasAtLeastOneEnteringStateTransition = false;
            for (final OperationResult operationResult : OperationResult.values()) {
                try {
                    DefaultTransition.findTransition(initialState, operation, operationResult);
                    hasAtLeastOneEnteringStateTransition = true;
                    break;
                } catch (final MissingEntryException ignored) {
                }
            }
            if (!hasAtLeastOneEnteringStateTransition) {
                throw new MissingEntryException("No entering state transition exists from state " + initialState.getName() + " for operation " + operation.getName());
            }

            leavingStateCallback.leavingState(initialState);

            result = operation.run(operationCallback);
            transition = DefaultTransition.findTransition(initialState, operation, result);
        } catch (final OperationException e) {
            rethrowableException = e;

            transition = DefaultTransition.findTransition(initialState, operation, e.getOperationResult());
        } catch (final RuntimeException e) {
            rethrowableException = new OperationException(e);
        } finally {
            if (transition != null) {
                enteringStateCallback.enteringState(transition.getFinalState(), operationCallback, result, leavingStateCallback);
            }
            if (rethrowableException != null) {
                throw rethrowableException;
            }
        }
    }

    public void setName(final String name) {
        this.name = name;
    }

    public DefaultStateMachine getStateMachine() {
        return stateMachine;
    }

    public void setStateMachine(final DefaultStateMachine stateMachine) {
        this.stateMachine = stateMachine;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final DefaultState that = (DefaultState) o;

        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public void writeExternal(final ObjectOutput out) throws IOException {
        out.writeUTF(name);
    }

    @Override
    public void readExternal(final ObjectInput in) throws IOException, ClassNotFoundException {
        this.name = in.readUTF();
    }
}