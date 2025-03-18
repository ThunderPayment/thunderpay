/**
 * @file AuditLog.java
 * @author Krisna Pranav
 * @brief Audit Log
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.util.audit;

import java.util.UUID;
import org.joda.time.DateTime;
import org.thunderpay.billing.ObjectType;
import org.thunderpay.billing.util.entity.Entity;

public interface AuditLog extends Entity {
    public UUID getAuditedEntityId();

    public ObjectType getAuditedObjectType();

    public ChangeType getChangeType();

    public String getUserName();

    public DateTime getCreatedDate();

    public String getReasonCode();

    public String getUserToken();

    public String getComment();
}