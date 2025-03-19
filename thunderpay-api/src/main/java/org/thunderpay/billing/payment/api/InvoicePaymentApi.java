/**
 * @file InvoicePaymentApi.java
 * @author Krisna Pranav
 * @brief Invoice Payment Api
 * @version 1.0
 * @date 2025-03-19
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.payment.api;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.joda.time.DateTime;
import org.thunderpay.billing.ThunderApi;
import org.thunderpay.billing.account.api.Account;
import org.thunderpay.billing.catalog.api.Currency;
import org.thunderpay.billing.invoice.api.InvoicePayment;
import org.thunderpay.billing.security.RequiresPermissions;
import org.thunderpay.billing.util.callcontext.CallContext;
import org.thunderpay.billing.util.callcontext.TenantContext;

import static org.thunderpay.billing.security.Permission.PAYMENT_CAN_CHARGEBACK;
import static org.thunderpay.billing.security.Permission.PAYMENT_CAN_REFUND;
import static org.thunderpay.billing.security.Permission.PAYMENT_CAN_TRIGGER_PAYMENT;

public interface InvoicePaymentApi extends ThunderApi {
    public InvoicePayment getInvoicePayment(UUID invoicePaymentId, TenantContext context)
            throws PaymentApiException;


    @RequiresPermissions(PAYMENT_CAN_TRIGGER_PAYMENT)
    public InvoicePayment createPurchaseForInvoicePayment(Account account,
                                                          UUID invoiceId,
                                                          UUID paymentMethodId,
                                                          UUID paymentId,
                                                          BigDecimal amount,
                                                          Currency currency,
                                                          DateTime effectiveDate,
                                                          String paymentExternalKey,
                                                          String paymentTransactionExternalKey,
                                                          Iterable<PluginProperty> properties,
                                                          PaymentOptions paymentOptions,
                                                          CallContext context) throws PaymentApiException;

    @RequiresPermissions(PAYMENT_CAN_REFUND)
    public InvoicePayment createRefundForInvoicePayment(boolean isAdjusted,
                                                        Map<UUID, BigDecimal> adjustments,
                                                        Account account,
                                                        UUID paymentId,
                                                        BigDecimal amount,
                                                        Currency currency,
                                                        DateTime effectiveDate,
                                                        String paymentTransactionExternalKey,
                                                        Iterable<PluginProperty> properties,
                                                        PaymentOptions paymentOptions,
                                                        CallContext context) throws PaymentApiException;

    @RequiresPermissions(PAYMENT_CAN_TRIGGER_PAYMENT)
    public InvoicePayment createCreditForInvoicePayment(boolean isAdjusted,
                                                        Map<UUID, BigDecimal> adjustments,
                                                        Account account,
                                                        UUID originalPaymentId,
                                                        UUID paymentMethodId,
                                                        UUID paymentId,
                                                        BigDecimal amount,
                                                        Currency currency,
                                                        DateTime effectiveDate,
                                                        String paymentExternalKey,
                                                        String paymentTransactionExternalKey,
                                                        Iterable<PluginProperty> properties,
                                                        PaymentOptions paymentOptions,
                                                        CallContext context) throws PaymentApiException;

    @RequiresPermissions(PAYMENT_CAN_CHARGEBACK)
    public InvoicePayment createChargebackForInvoicePayment(Account account,
                                                            UUID paymentId,
                                                            BigDecimal amount,
                                                            Currency currency,
                                                            DateTime effectiveDate,
                                                            String paymentTransactionExternalKey,
                                                            Iterable<PluginProperty> properties,
                                                            PaymentOptions paymentOptions,
                                                            CallContext context) throws PaymentApiException;

    @RequiresPermissions(PAYMENT_CAN_CHARGEBACK)
    public InvoicePayment createChargebackReversalForInvoicePayment(Account account,
                                                                    UUID paymentId,
                                                                    DateTime effectiveDate,
                                                                    String paymentTransactionExternalKey,
                                                                    Iterable<PluginProperty> properties,
                                                                    PaymentOptions paymentOptions,
                                                                    CallContext context) throws PaymentApiException;

    public List<InvoicePayment> getInvoicePayments(UUID paymentId, TenantContext context);

    public List<InvoicePayment> getInvoicePaymentsByAccount(UUID accountId, TenantContext context);
}