/**
 * @file DefaultStateMachineConfig.java
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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import org.thunderpay.xmlloader.ValidationErrors;

@XmlRootElement(name = "stateMachineConfig")
@XmlAccessorType(XmlAccessType.NONE)
public class DefaultStateMachineConfig extends StateMachineValidatingConfig<DefaultStateMachineConfig> implements StateMachineConfig, Externalizable {

    @XmlElementWrapper(name = "stateMachines", required = true)
    @XmlElement(name = "stateMachine", required = true)
    private DefaultStateMachine[] stateMachines;

    @XmlElementWrapper(name = "linkStateMachines", required = false)
    @XmlElement(name = "linkStateMachine", required = false)
    private DefaultLinkStateMachine[] linkStateMachines = new DefaultLinkStateMachine[0];

    public DefaultStateMachineConfig() {
    }

    @Override
    public void initialize(final DefaultStateMachineConfig root) {
        for (final DefaultStateMachine cur : stateMachines) {
            cur.initialize(root);
        }
        for (final DefaultLinkStateMachine cur : linkStateMachines) {
            cur.initialize(root);
        }
    }

    @Override
    public ValidationErrors validate(final DefaultStateMachineConfig root, final ValidationErrors errors) {
        validateCollection(root, errors, stateMachines);
        validateCollection(root, errors, linkStateMachines);
        return errors;
    }

    @Override
    public StateMachine getStateMachine(final String stateMachineName) throws MissingEntryException {
        return (StateMachine) getEntry(stateMachines, stateMachineName);
    }

    @Override
    public LinkStateMachine getLinkStateMachine(final String linkStateMachineName) throws MissingEntryException {
        return (LinkStateMachine) getEntry(linkStateMachines, linkStateMachineName);
    }

    @Override
    public LinkStateMachine[] getLinkStateMachines() {
        return linkStateMachines;
    }

    @Override
    public StateMachine getStateMachineForState(final String stateName) throws MissingEntryException {
        for (final DefaultStateMachine cur : stateMachines) {
            for (final State st : cur.getStates()) {
                if (st.getName().equals(stateName)) {
                    return cur;
                }
            }
        }
        throw new MissingEntryException("Cannot find stateMachine associated with state" + stateName);
    }

    public DefaultStateMachine[] getStateMachines() {
        return stateMachines;
    }

    public void setStateMachines(final DefaultStateMachine[] stateMachines) {
        this.stateMachines = stateMachines;
    }

    public void setLinkStateMachines(final DefaultLinkStateMachine[] linkStateMachines) {
        this.linkStateMachines = linkStateMachines;
    }

    public LinkStateMachine findLinkStateMachine(final StateMachine srcStateMachine, final State srcState, final StateMachine dstStateMachine) throws MissingEntryException {

        try {
            return Stream.of(linkStateMachines)
                    .filter(input -> input != null &&
                            input.getInitialStateMachine().getName().equals(srcStateMachine.getName()) &&
                            input.getInitialState().getName().equals(srcState.getName()) &&
                            input.getFinalStateMachine().getName().equals(dstStateMachine.getName()))
                    .findFirst().get();
        } catch (final NoSuchElementException e) {
            throw new MissingEntryException("Missing transition for srcStateMachine " + srcStateMachine.getName() +
                    ", srcState = " + srcState.getName() + ", dstStateMachine = " + dstStateMachine.getName(), e);
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

        final DefaultStateMachineConfig that = (DefaultStateMachineConfig) o;

        if (!Arrays.equals(stateMachines, that.stateMachines)) {
            return false;
        }
        return Arrays.equals(linkStateMachines, that.linkStateMachines);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(stateMachines);
        result = 31 * result + Arrays.hashCode(linkStateMachines);
        return result;
    }

    @Override
    public void writeExternal(final ObjectOutput out) throws IOException {
        out.writeObject(stateMachines);
        out.writeObject(linkStateMachines);
    }

    @Override
    public void readExternal(final ObjectInput in) throws IOException, ClassNotFoundException {
        this.stateMachines = (DefaultStateMachine[]) in.readObject();
        this.linkStateMachines = (DefaultLinkStateMachine[]) in.readObject();
    }
}