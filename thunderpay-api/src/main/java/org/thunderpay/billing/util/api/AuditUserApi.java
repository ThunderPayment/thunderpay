/**
 * @file AuditUserApi.java
 * @author Krisna Pranav
 * @brief Audit Use API
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.util.api;


import java.util.List;
import java.util.UUID;

import org.thunderpay.billing.ThunderApi;
import org.thunderpay.billing.ObjectType;
import org.thunderpay.billing.util.audit.AccountAuditLogs;
import org.thunderpay.billing.util.audit.AccountAuditLogsForObjectType;
import org.thunderpay.billing.util.audit.AuditLog;
import org.thunderpay.billing.util.callcontext.TenantContext;

public interface AuditUserApi extends ThunderApi {

    public AccountAuditLogs getAccountAuditLogs(UUID accountId, AuditLevel auditLevel, TenantContext context);

    public AccountAuditLogsForObjectType getAccountAuditLogs(UUID accountId, ObjectType objectType, AuditLevel auditLevel, TenantContext context);

    public List<AuditLog> getAuditLogs(UUID objectId, ObjectType objectType, AuditLevel auditLevel, TenantContext context);
}