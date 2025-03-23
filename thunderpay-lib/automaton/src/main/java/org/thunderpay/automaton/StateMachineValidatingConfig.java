/**
 * @file StateMachineValidatingConfig.java
 * @author Krisna Pranav
 * @version 1.0
 * @date 2025-03-22
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.automaton;

import org.thunderpay.xmlloader.ValidatingConfig;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.NONE)
public abstract class StateMachineValidatingConfig<Context> extends ValidatingConfig<Context> {

    protected StateMachineEntry getEntry(final StateMachineEntry [] entries, final String entryName) throws MissingEntryException {
        for (final StateMachineEntry cur : entries) {
            if (cur.getName().equals(entryName)) {
                return cur;
            }
        }
        throw new MissingEntryException("Unknown entry " + entryName);
    }

}
