/**
 * @file AccountAuditLogs.java
 * @author Krisna Pranav
 * @brief Account Audit Logs
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.util.audit;


import java.util.List;
import java.util.UUID;
import org.thunderpay.billing.ObjectType;

public interface AccountAuditLogs {

    public List<AuditLog> getAuditLogsForAccount();

    public List<AuditLog> getAuditLogsForBundle(UUID bundleId);

    public List<AuditLog> getAuditLogsForSubscription(UUID subscriptionId);

    public List<AuditLog> getAuditLogsForSubscriptionEvent(UUID subscriptionEventId);

    public List<AuditLog> getAuditLogsForBlockingState(UUID blockingStateId);

    public List<AuditLog> getAuditLogsForInvoice(UUID invoiceId);

    public List<AuditLog> getAuditLogsForInvoiceItem(UUID invoiceItemId);

    public List<AuditLog> getAuditLogsForInvoicePayment(UUID invoicePaymentId);

    public List<AuditLog> getAuditLogsForPayment(UUID paymentId);

    public List<AuditLog> getAuditLogsForPaymentAttempt(UUID paymentAttemptId);

    public List<AuditLog> getAuditLogsForPaymentTransaction(UUID paymentTransactionId);

    public List<AuditLog> getAuditLogsForPaymentMethod(UUID paymentMethodId);

    public List<AuditLog> getAuditLogsForTag(UUID tagId);

    public List<AuditLog> getAuditLogsForCustomField(UUID customFieldId);

    public AccountAuditLogsForObjectType getAuditLogs(ObjectType objectType);

    public List<AuditLog> getAuditLogs();
}
