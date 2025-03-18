package org.thunderpay.billing.util.audit;

import org.thunderpay.billing.util.entity.Entity;

public interface AuditLogWithHistory<E extends Entity> extends AuditLog {

    public E getEntity();
}