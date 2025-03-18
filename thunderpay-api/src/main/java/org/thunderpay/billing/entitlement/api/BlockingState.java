package org.thunderpay.billing.entitlement.api;

import java.util.UUID;
import org.joda.time.DateTime;
import org.thunderpay.billing.util.entity.Entity;

public interface BlockingState extends Entity, Comparable<BlockingState> {

    public UUID getBlockedId();

    public String getStateName();

    public BlockingStateType getType();

    public DateTime getEffectiveDate();

    public boolean isBlockChange();

    public boolean isBlockEntitlement();

    public boolean isBlockBilling();

    public int compareTo(BlockingState arg0);

    public int hashCode();

    public String getDescription();

    public String toString();

    public String getService();

    public boolean isActive();
}