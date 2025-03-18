/**
 * @file AccountAuditLogsForObjectType.java
 * @author Krisna Pranav
 * @brief Account Audit Logs For Object Type
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.util.audit;

import java.util.List;
import java.util.UUID;

public interface AccountAuditLogsForObjectType {

    public List<AuditLog> getAuditLogs(UUID objectId);
}