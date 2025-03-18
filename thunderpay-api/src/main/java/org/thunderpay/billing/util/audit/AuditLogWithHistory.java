/**
 * @file AuditLogWithHistory.java
 * @author Krisna Pranav
 * @brief Audit Log With History
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.util.audit;

import org.thunderpay.billing.util.entity.Entity;

public interface AuditLogWithHistory<E extends Entity> extends AuditLog {

    public E getEntity();
}