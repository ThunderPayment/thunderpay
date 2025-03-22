/**
 * @file DeadEvent.java
 * @author Krisna Pranav
 * @brief Dead Event
 * @version 1.0
 * @date 2025-03-22
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.commons.eventbus;

import org.thunderpay.commons.utils.Preconditions;

public class DeadEvent {

    private final Object source;
    private final Object event;

    public DeadEvent(final Object source, final Object event) {
        this.source = Preconditions.checkNotNull(source);
        this.event = Preconditions.checkNotNull(event);
    }

    public Object getSource() {
        return source;
    }

    public Object getEvent() {
        return event;
    }

    @Override
    public String toString() {
        return "DeadEvent {" +
                "source=" + source +
                ", event=" + event +
                '}';
    }
}