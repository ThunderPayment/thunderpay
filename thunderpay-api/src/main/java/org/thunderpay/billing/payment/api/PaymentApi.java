/**
 * @file PaymentApi.java
 * @author Krisna Pranav
 * @brief Payment Api
 * @version 1.0
 * @date 2025-03-19
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.payment.api;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import org.joda.time.DateTime;
import org.thunderpay.billing.ThunderApi;
import org.thunderpay.billing.account.api.Account;
import org.thunderpay.billing.catalog.api.Currency;
import org.thunderpay.billing.security.RequiresPermissions;
import org.thunderpay.billing.util.api.AuditLevel;
import org.thunderpay.billing.util.audit.AuditLogWithHistory;
import org.thunderpay.billing.util.callcontext.CallContext;
import org.thunderpay.billing.util.callcontext.TenantContext;
import org.thunderpay.billing.util.entity.Pagination;
import static org.thunderpay.billing.security.Permission.PAYMENT_CAN_CHARGEBACK;
import static org.thunderpay.billing.security.Permission.PAYMENT_CAN_REFUND;
import static org.thunderpay.billing.security.Permission.PAYMENT_CAN_TRANSITION;
import static org.thunderpay.billing.security.Permission.PAYMENT_CAN_TRIGGER_PAYMENT;
import static org.thunderpay.billing.security.Permission.PAYMENT_METHOD_CAN_CREATE;
import static org.thunderpay.billing.security.Permission.PAYMENT_METHOD_CAN_DELETE;
import static org.thunderpay.billing.security.Permission.PAYMENT_METHOD_CAN_UPDATE;

public interface PaymentApi extends ThunderApi {
    @RequiresPermissions(PAYMENT_CAN_TRIGGER_PAYMENT)
    public Payment createAuthorization(Account account, UUID paymentMethodId, UUID paymentId, BigDecimal amount, Currency currency, DateTime effectiveDate,
                                       String paymentExternalKey, String paymentTransactionExternalKey,
                                       Iterable<PluginProperty> properties, CallContext context)
            throws PaymentApiException;


    @RequiresPermissions(PAYMENT_CAN_TRIGGER_PAYMENT)
    public Payment createAuthorizationWithPaymentControl(Account account, UUID paymentMethodId, UUID paymentId, BigDecimal amount, Currency currency, DateTime effectiveDate,
                                                         String paymentExternalKey, String paymentTransactionExternalKey,
                                                         Iterable<PluginProperty> properties, PaymentOptions paymentOptions, CallContext context)
            throws PaymentApiException;


    @RequiresPermissions(PAYMENT_CAN_TRIGGER_PAYMENT)
    public Payment createCapture(Account account, UUID paymentId, BigDecimal amount, Currency currency, DateTime effectiveDate,
                                 String paymentTransactionExternalKey, Iterable<PluginProperty> properties,
                                 CallContext context)
            throws PaymentApiException;


    @RequiresPermissions(PAYMENT_CAN_TRIGGER_PAYMENT)
    public Payment createCaptureWithPaymentControl(Account account, UUID paymentId, BigDecimal amount, Currency currency, DateTime effectiveDate,
                                                   String paymentTransactionExternalKey, Iterable<PluginProperty> properties,
                                                   PaymentOptions paymentOptions, CallContext context)
            throws PaymentApiException;


    @RequiresPermissions(PAYMENT_CAN_TRIGGER_PAYMENT)
    public Payment createPurchase(Account account, UUID paymentMethodId, UUID paymentId, BigDecimal amount, Currency currency, DateTime effectiveDate,
                                  String paymentExternalKey, String paymentTransactionExternalKey,
                                  Iterable<PluginProperty> properties, CallContext context)
            throws PaymentApiException;

    @RequiresPermissions(PAYMENT_CAN_TRIGGER_PAYMENT)
    public Payment createPurchaseWithPaymentControl(Account account, UUID paymentMethodId, UUID paymentId, BigDecimal amount, Currency currency, DateTime effectiveDate,
                                                    String paymentExternalKey, String paymentTransactionExternalKey,
                                                    Iterable<PluginProperty> properties, PaymentOptions paymentOptions, CallContext context)
            throws PaymentApiException;

    @RequiresPermissions(PAYMENT_CAN_TRIGGER_PAYMENT)
    public Payment createVoid(Account account, UUID paymentId, DateTime effectiveDate, String paymentTransactionExternalKey, Iterable<PluginProperty> properties,
                              CallContext context)
            throws PaymentApiException;


    @RequiresPermissions(PAYMENT_CAN_TRIGGER_PAYMENT)
    public Payment createVoidWithPaymentControl(Account account, UUID paymentId, DateTime effectiveDate, String paymentTransactionExternalKey, Iterable<PluginProperty> properties,
                                                PaymentOptions paymentOptions, CallContext context)
            throws PaymentApiException;



    @RequiresPermissions(PAYMENT_CAN_REFUND)
    public Payment createRefund(Account account, UUID paymentId, BigDecimal amount, Currency currency, DateTime effectiveDate,
                                String paymentTransactionExternalKey, Iterable<PluginProperty> properties,
                                CallContext context)
            throws PaymentApiException;

    @RequiresPermissions(PAYMENT_CAN_REFUND)
    public Payment createRefundWithPaymentControl(Account account, UUID paymentId, BigDecimal amount, Currency currency, DateTime effectiveDate,
                                                  String paymentTransactionExternalKey, Iterable<PluginProperty> properties,
                                                  PaymentOptions paymentOptions, CallContext context)
            throws PaymentApiException;


    @RequiresPermissions(PAYMENT_CAN_TRIGGER_PAYMENT)
    public Payment createCredit(Account account, UUID paymentMethodId, UUID paymentId, BigDecimal amount, Currency currency, DateTime effectiveDate,
                                String paymentExternalKey, String paymentTransactionExternalKey,
                                Iterable<PluginProperty> properties, CallContext context)
            throws PaymentApiException;


    @RequiresPermissions(PAYMENT_CAN_TRIGGER_PAYMENT)
    public Payment createCreditWithPaymentControl(Account account, UUID paymentMethodId, UUID paymentId, BigDecimal amount, Currency currency, DateTime effectiveDate,
                                                  String paymentExternalKey, String paymentTransactionExternalKey,
                                                  Iterable<PluginProperty> properties, PaymentOptions paymentOptions, CallContext context)
            throws PaymentApiException;

    @RequiresPermissions(PAYMENT_CAN_TRIGGER_PAYMENT)
    public void cancelScheduledPaymentTransaction(String paymentTransactionExternalKey, CallContext context)
            throws PaymentApiException;

    @RequiresPermissions(PAYMENT_CAN_TRIGGER_PAYMENT)
    public void cancelScheduledPaymentTransaction(UUID paymentTransactionId, CallContext context)
            throws PaymentApiException;


    @RequiresPermissions(PAYMENT_CAN_CHARGEBACK)
    public Payment createChargeback(Account account, UUID paymentId, BigDecimal amount, Currency currency, DateTime effectiveDate, String paymentTransactionExternalKey, CallContext context) throws PaymentApiException;

    @RequiresPermissions(PAYMENT_CAN_CHARGEBACK)
    public Payment createChargebackWithPaymentControl(Account account, UUID paymentId, BigDecimal amount, Currency currency, DateTime effectiveDate, String paymentTransactionExternalKey, final PaymentOptions paymentOptions, CallContext context) throws PaymentApiException;

    @RequiresPermissions(PAYMENT_CAN_CHARGEBACK)
    public Payment createChargebackReversal(Account account, UUID paymentId, DateTime effectiveDate, String paymentTransactionExternalKey, CallContext context) throws PaymentApiException;

    @RequiresPermissions(PAYMENT_CAN_CHARGEBACK)
    public Payment createChargebackReversalWithPaymentControl(Account account, UUID paymentId, DateTime effectiveDate, String paymentTransactionExternalKey, PaymentOptions paymentOptions, CallContext context) throws PaymentApiException;

    @RequiresPermissions(PAYMENT_CAN_TRANSITION)
    public Payment notifyPendingTransactionOfStateChanged(Account account, UUID paymentTransactionId, boolean isSuccess, CallContext context) throws PaymentApiException;

    @RequiresPermissions(PAYMENT_CAN_TRANSITION)
    public Payment notifyPendingTransactionOfStateChangedWithPaymentControl(Account account, UUID paymentTransactionId, boolean isSuccess, PaymentOptions paymentOptions, CallContext context) throws PaymentApiException;

    public List<Payment> getAccountPayments(UUID accountId, boolean withPluginInfo, boolean withAttempts, Iterable<PluginProperty> properties, TenantContext context)
            throws PaymentApiException;

    public Payment getPayment(UUID paymentId, boolean withPluginInfo, boolean withAttempts, Iterable<PluginProperty> properties, TenantContext context)
            throws PaymentApiException;

    public Payment getPaymentByExternalKey(String paymentExternalKey, boolean withPluginInfo, boolean withAttempts, Iterable<PluginProperty> properties, TenantContext context)
            throws PaymentApiException;

    public Payment getPaymentByTransactionId(final UUID transactionId, final boolean withPluginInfo, final boolean withAttempts, final Iterable<PluginProperty> properties, final TenantContext context) throws PaymentApiException;

    public Payment getPaymentByTransactionExternalKey(final String transactionExternalKey, final boolean withPluginInfo, final boolean withAttempts, final Iterable<PluginProperty> properties, final TenantContext context) throws PaymentApiException;

    public Pagination<Payment> getPayments(Long offset, Long limit, boolean withPluginInfo, boolean withAttempts, Iterable<PluginProperty> properties, TenantContext context);

    public Pagination<Payment> getPayments(Long offset, Long limit, String pluginName, boolean withPluginInfo, boolean withAttempts, Iterable<PluginProperty> properties, TenantContext context) throws PaymentApiException;

    public Pagination<Payment> searchPayments(String searchKey, Long offset, Long limit, boolean withPluginInfo, boolean withAttempts, Iterable<PluginProperty> properties, TenantContext context);

    public Pagination<Payment> searchPayments(String searchKey, Long offset, Long limit, String pluginName, boolean withPluginInfo, boolean withAttempts, Iterable<PluginProperty> properties, TenantContext context) throws PaymentApiException;

    @RequiresPermissions(PAYMENT_METHOD_CAN_CREATE)
    public UUID addPaymentMethod(Account account, String paymentMethodExternalKey, String pluginName, boolean setDefault, PaymentMethodPlugin paymentMethodInfo, Iterable<PluginProperty> properties, CallContext context)
            throws PaymentApiException;

    @RequiresPermissions(PAYMENT_METHOD_CAN_CREATE)
    public UUID addPaymentMethodWithPaymentControl(Account account, String paymentMethodExternalKey, String pluginName, boolean setDefault, PaymentMethodPlugin paymentMethodInfo, Iterable<PluginProperty> properties, PaymentOptions paymentOptions, CallContext context)
            throws PaymentApiException;


    public List<PaymentMethod> getAccountPaymentMethods(UUID accountId, boolean includedInactive, boolean withPluginInfo, Iterable<PluginProperty> properties, TenantContext context)
            throws PaymentApiException;

    public PaymentMethod getPaymentMethodById(UUID paymentMethodId, boolean includedInactive, boolean withPluginInfo, Iterable<PluginProperty> properties, TenantContext context)
            throws PaymentApiException;

    public PaymentMethod getPaymentMethodByExternalKey(String paymentMethodExternalKey, boolean includedInactive, boolean withPluginInfo, Iterable<PluginProperty> properties, TenantContext context)
            throws PaymentApiException;

    public Pagination<PaymentMethod> getPaymentMethods(Long offset, Long limit, boolean withPluginInfo, Iterable<PluginProperty> properties, TenantContext context);

    public Pagination<PaymentMethod> getPaymentMethods(Long offset, Long limit, String pluginName, boolean withPluginInfo, Iterable<PluginProperty> properties, TenantContext context) throws PaymentApiException;

    public Pagination<PaymentMethod> searchPaymentMethods(String searchKey, Long offset, Long limit, boolean withPluginInfo, Iterable<PluginProperty> properties, TenantContext context);

    public Pagination<PaymentMethod> searchPaymentMethods(String searchKey, Long offset, Long limit, String pluginName, boolean withPluginInfo, Iterable<PluginProperty> properties, TenantContext context) throws PaymentApiException;

    @RequiresPermissions(PAYMENT_METHOD_CAN_DELETE)
    public void deletePaymentMethod(Account account, UUID paymentMethodId, boolean deleteDefaultPaymentMethodWithAutoPayOff, boolean forceDefaultPaymentMethodDeletion, Iterable<PluginProperty> properties, CallContext context)
            throws PaymentApiException;

    @RequiresPermissions(PAYMENT_METHOD_CAN_CREATE)
    public void setDefaultPaymentMethod(Account account, UUID paymentMethodId, Iterable<PluginProperty> properties, CallContext context)
            throws PaymentApiException;

    @RequiresPermissions(PAYMENT_METHOD_CAN_UPDATE)
    public List<PaymentMethod> refreshPaymentMethods(Account account, String pluginName, Iterable<PluginProperty> properties, CallContext context)
            throws PaymentApiException;

    @RequiresPermissions(PAYMENT_METHOD_CAN_UPDATE)
    public List<PaymentMethod> refreshPaymentMethods(Account account, Iterable<PluginProperty> properties, CallContext context)
            throws PaymentApiException;


    List<AuditLogWithHistory> getPaymentAuditLogsWithHistoryForId(UUID paymentId, AuditLevel auditLevel, TenantContext context);

    List<AuditLogWithHistory> getPaymentMethodAuditLogsWithHistoryForId(UUID paymentMethodId, AuditLevel auditLevel, TenantContext context);

    List<AuditLogWithHistory> getPaymentAttemptAuditLogsWithHistoryForId(UUID paymentAttemptId, AuditLevel auditLevel, TenantContext context);

    List<AuditLogWithHistory> getPaymentTransactionAuditLogsWithHistoryForId(UUID paymentTransactionId, AuditLevel auditLevel, TenantContext context);


}