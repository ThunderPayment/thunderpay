/**
 * @file DefaultLinkStateMachine.java
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
public class DefaultLinkStateMachine extends StateMachineValidatingConfig<DefaultStateMachineConfig> implements LinkStateMachine, Externalizable {

    @XmlElement(name = "initialStateMachine", required = true)
    @XmlIDREF
    private DefaultStateMachine initialStateMachine;

    @XmlElement(name = "initialState", required = true)
    @XmlIDREF
    private DefaultState initialState;

    @XmlElement(name = "finalStateMachine", required = true)
    @XmlIDREF
    private DefaultStateMachine finalStateMachine;

    @XmlElement(name = "finalState", required = true)
    @XmlIDREF
    private DefaultState finalState;

    public DefaultLinkStateMachine() {
    }

    @Override
    public String getName() {
        return initialStateMachine.getName() + "-" + finalStateMachine.getName();
    }

    @Override
    public StateMachine getInitialStateMachine() {
        return initialStateMachine;
    }

    @Override
    public State getInitialState() {
        return initialState;
    }

    @Override
    public StateMachine getFinalStateMachine() {
        return finalStateMachine;
    }

    @Override
    public State getFinalState() {
        return finalState;
    }

    @Override
    public ValidationErrors validate(final DefaultStateMachineConfig root, final ValidationErrors errors) {
        return errors;
    }

    public void setInitialStateMachine(final DefaultStateMachine initialStateMachine) {
        this.initialStateMachine = initialStateMachine;
    }

    public void setInitialState(final DefaultState initialState) {
        this.initialState = initialState;
    }

    public void setFinalStateMachine(final DefaultStateMachine finalStateMachine) {
        this.finalStateMachine = finalStateMachine;
    }

    public void setFinalState(final DefaultState finalState) {
        this.finalState = finalState;
    }

    public static LinkStateMachine findLinkStateMachine(final StateMachine srcStateMachine, final State srcState, final StateMachine dstStateMachine) throws MissingEntryException {
        return ((DefaultStateMachine) srcStateMachine).getStateMachineConfig().findLinkStateMachine(srcStateMachine, srcState, dstStateMachine);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final DefaultLinkStateMachine that = (DefaultLinkStateMachine) o;

        if (initialStateMachine != null ? !initialStateMachine.equals(that.initialStateMachine) : that.initialStateMachine != null) {
            return false;
        }
        if (initialState != null ? !initialState.equals(that.initialState) : that.initialState != null) {
            return false;
        }
        if (finalStateMachine != null ? !finalStateMachine.equals(that.finalStateMachine) : that.finalStateMachine != null) {
            return false;
        }
        return finalState != null ? finalState.equals(that.finalState) : that.finalState == null;
    }

    @Override
    public int hashCode() {
        int result = initialStateMachine != null ? initialStateMachine.hashCode() : 0;
        result = 31 * result + (initialState != null ? initialState.hashCode() : 0);
        result = 31 * result + (finalStateMachine != null ? finalStateMachine.hashCode() : 0);
        result = 31 * result + (finalState != null ? finalState.hashCode() : 0);
        return result;
    }

    @Override
    public void writeExternal(final ObjectOutput out) throws IOException {
        out.writeObject(initialStateMachine);
        out.writeObject(initialState);
        out.writeObject(finalStateMachine);
        out.writeObject(finalState);
    }

    @Override
    public void readExternal(final ObjectInput in) throws IOException, ClassNotFoundException {
        this.initialStateMachine = (DefaultStateMachine) in.readObject();
        this.initialState = (DefaultState) in.readObject();
        this.finalStateMachine = (DefaultStateMachine) in.readObject();
        this.finalState = (DefaultState) in.readObject();
    }
}