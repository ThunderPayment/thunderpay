/**
 * @file DefaultOperation.java
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
public class DefaultOperation extends StateMachineValidatingConfig<DefaultStateMachineConfig> implements Operation, Externalizable {

    @XmlAttribute(required = true)
    @XmlID
    private String name;

    private DefaultStateMachine stateMachine;

    public DefaultOperation() {
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public StateMachine getStateMachine() {
        return stateMachine;
    }

    @Override
    public OperationResult run(final OperationCallback cb) throws OperationException {
        return cb.doOperationCallback();
    }

    @Override
    public ValidationErrors validate(final DefaultStateMachineConfig root, final ValidationErrors errors) {
        return errors;
    }

    public void setName(final String name) {
        this.name = name;
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

        final DefaultOperation that = (DefaultOperation) o;

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