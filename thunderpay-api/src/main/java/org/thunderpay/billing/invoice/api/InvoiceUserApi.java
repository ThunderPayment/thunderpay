/**
 * @file InvoiceUserApi.java
 * @author Krisna Pranav
 * @brief Invoice User Api
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.invoice.api;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.joda.time.LocalDate;
import org.thunderpay.billing.ThunderApi;
import org.thunderpay.billing.account.api.AccountApiException;
import org.thunderpay.billing.catalog.api.Currency;
import org.thunderpay.billing.payment.api.PluginProperty;
import org.thunderpay.billing.security.RequiresPermissions;
import org.thunderpay.billing.util.api.AuditLevel;
import org.thunderpay.billing.util.api.TagApiException;
import org.thunderpay.billing.util.audit.AuditLogWithHistory;
import org.thunderpay.billing.util.callcontext.CallContext;
import org.thunderpay.billing.util.callcontext.TenantContext;
import org.thunderpay.billing.util.entity.Pagination;
import static org.thunderpay.billing.security.Permission.ACCOUNT_CAN_CHARGE;
import static org.thunderpay.billing.security.Permission.ACCOUNT_CAN_CREDIT;
import static org.thunderpay.billing.security.Permission.INVOICE_CAN_COMMIT;
import static org.thunderpay.billing.security.Permission.INVOICE_CAN_CREDIT;
import static org.thunderpay.billing.security.Permission.INVOICE_CAN_DELETE_CBA;
import static org.thunderpay.billing.security.Permission.INVOICE_CAN_DRY_RUN_INVOICE;
import static org.thunderpay.billing.security.Permission.INVOICE_CAN_ITEM_ADJUST;
import static org.thunderpay.billing.security.Permission.INVOICE_CAN_TRIGGER_INVOICE;
import static org.thunderpay.billing.security.Permission.INVOICE_CAN_VOID;
import static org.thunderpay.billing.security.Permission.INVOICE_CAN_WRITE_OFF;

public interface InvoiceUserApi extends ThunderApi {
    public List<Invoice> getInvoicesByAccount(UUID accountId, boolean includesMigrated, boolean includeVoidedInvoices, boolean includeInvoiceComponents, TenantContext context);

    public Pagination<Invoice> getInvoicesByAccount(UUID accountId, Long offset, Long limit, TenantContext context);

    public List<Invoice> getInvoicesByAccount(UUID accountId, LocalDate fromDate, LocalDate upToDate, boolean includeVoidedInvoices, boolean includeInvoiceComponents, TenantContext context);

    public List<Invoice> getInvoicesByGroup(UUID accountId, UUID groupId, TenantContext context);


    public Pagination<Invoice> getInvoices(Long offset, Long limit, TenantContext context);

    public Pagination<Invoice> searchInvoices(String searchKey, Long offset, Long limit, TenantContext context);

    public BigDecimal getAccountBalance(UUID accountId, TenantContext context);

    public BigDecimal getAccountCBA(UUID accountId, TenantContext context);

    public Invoice getInvoice(UUID invoiceId, TenantContext context) throws InvoiceApiException;

    public Invoice getInvoiceByPayment(UUID paymentId, TenantContext context) throws InvoiceApiException;

    public Invoice getInvoiceByNumber(Integer number, TenantContext context) throws InvoiceApiException;

    public Invoice getInvoiceByInvoiceItem(UUID invoiceItemId, TenantContext context) throws InvoiceApiException;

    public Collection<Invoice> getUnpaidInvoicesByAccountId(UUID accountId, LocalDate fromDate, LocalDate upToDate, TenantContext context);

    @RequiresPermissions(INVOICE_CAN_TRIGGER_INVOICE)
    public Invoice triggerInvoiceGeneration(UUID accountId, LocalDate targetDate, Iterable<PluginProperty> properties, CallContext context) throws InvoiceApiException;

    @RequiresPermissions(INVOICE_CAN_TRIGGER_INVOICE)
    public Iterable<Invoice> triggerInvoiceGroupGeneration(UUID accountId, LocalDate targetDate, Iterable<PluginProperty> properties, CallContext context) throws InvoiceApiException;


    @RequiresPermissions(INVOICE_CAN_DRY_RUN_INVOICE)
    public Invoice triggerDryRunInvoiceGeneration(UUID accountId, LocalDate targetDate, DryRunArguments dryRunArguments, Iterable<PluginProperty> properties, CallContext context) throws InvoiceApiException;


    @RequiresPermissions(INVOICE_CAN_WRITE_OFF)
    public void tagInvoiceAsWrittenOff(UUID invoiceId, CallContext context) throws TagApiException, InvoiceApiException;

    @RequiresPermissions(INVOICE_CAN_WRITE_OFF)
    public void tagInvoiceAsNotWrittenOff(UUID invoiceId, CallContext context) throws TagApiException, InvoiceApiException;

    public InvoiceItem getExternalChargeById(UUID externalChargeId, TenantContext context) throws InvoiceApiException;

    @RequiresPermissions(ACCOUNT_CAN_CHARGE)
    public List<InvoiceItem> insertExternalCharges(UUID accountId, LocalDate effectiveDate, Iterable<InvoiceItem> charges, boolean autoCommit, Iterable<PluginProperty> properties, CallContext context) throws InvoiceApiException;


    @RequiresPermissions(ACCOUNT_CAN_CHARGE)
    public List<InvoiceItem> insertTaxItems(UUID accountId, LocalDate effectiveDate, Iterable<InvoiceItem> taxes, boolean autoCommit, Iterable<PluginProperty> properties, CallContext context) throws InvoiceApiException;


    public InvoiceItem getCreditById(UUID creditId, TenantContext context) throws InvoiceApiException;

    @RequiresPermissions(ACCOUNT_CAN_CREDIT)
    public List<InvoiceItem> insertCredits(UUID accountId, LocalDate effectiveDate, Iterable<InvoiceItem> creditItems, boolean autoCommit, Iterable<PluginProperty> properties, CallContext context) throws InvoiceApiException;


    @RequiresPermissions(INVOICE_CAN_ITEM_ADJUST)
    public InvoiceItem insertInvoiceItemAdjustment(UUID accountId, UUID invoiceId, UUID invoiceItemId, LocalDate effectiveDate,
                                                   String description, String itemDetails, Iterable<PluginProperty> properties, CallContext context) throws InvoiceApiException;

    @RequiresPermissions(INVOICE_CAN_ITEM_ADJUST)
    public InvoiceItem insertInvoiceItemAdjustment(UUID accountId, UUID invoiceId, UUID invoiceItemId, LocalDate effectiveDate,
                                                   BigDecimal amount, Currency currency, String description, String itemDetails, Iterable<PluginProperty> properties, CallContext context) throws InvoiceApiException;

    @RequiresPermissions(INVOICE_CAN_DELETE_CBA)
    public void deleteCBA(UUID accountId, UUID invoiceId, UUID invoiceItemId, CallContext context) throws InvoiceApiException;

    public String getInvoiceAsHTML(UUID invoiceId, TenantContext context) throws AccountApiException, IOException, InvoiceApiException;

    @RequiresPermissions(INVOICE_CAN_DELETE_CBA)
    public void consumeExistingCBAOnAccountWithUnpaidInvoices(final UUID accountId, final CallContext context);

    @RequiresPermissions(INVOICE_CAN_COMMIT)
    public void commitInvoice(UUID invoiceId, CallContext context) throws InvoiceApiException;

    @RequiresPermissions(INVOICE_CAN_TRIGGER_INVOICE)
    public UUID createMigrationInvoice(UUID accountId, LocalDate invoiceDate, Iterable<InvoiceItem> items, CallContext context);

    @RequiresPermissions(INVOICE_CAN_DELETE_CBA)
    public void transferChildCreditToParent(UUID childAccountId, CallContext context) throws InvoiceApiException;

    List<InvoiceItem> getInvoiceItemsByParentInvoice(UUID parentInvoiceId, final TenantContext context) throws InvoiceApiException;

    @RequiresPermissions(INVOICE_CAN_VOID)
    public void voidInvoice(UUID invoiceId, CallContext context) throws InvoiceApiException;

    List<AuditLogWithHistory> getInvoiceAuditLogsWithHistoryForId(UUID invoiceId, AuditLevel auditLevel, TenantContext context);

    List<AuditLogWithHistory> getInvoiceItemAuditLogsWithHistoryForId(UUID invoiceItemId, AuditLevel auditLevel, TenantContext context);

    List<AuditLogWithHistory> getInvoicePaymentAuditLogsWithHistoryForId(UUID invoicePaymentId, AuditLevel auditLevel, TenantContext context);

}