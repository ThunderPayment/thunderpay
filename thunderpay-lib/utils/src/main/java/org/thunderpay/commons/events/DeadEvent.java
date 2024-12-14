package org.thunderpay.commons.events;

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
        return "DeadEvent { " + "source=" + source + ", event=" + event + "}";
    }
}
