/**
 * @file OSGIThunderpay.java
 * @author Krisna Pranav
 * @brief OSGI Thunderpay
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.osgi.api;

import org.thunderpay.billing.account.api.AccountUserApi;
import org.thunderpay.billing.catalog.api.CatalogUserApi;
import org.thunderpay.billing.currency.api.CurrencyConversionApi;
import org.thunderpay.billing.entitlement.api.EntitlementApi;
import org.thunderpay.billing.entitlement.api.SubscriptionApi;
import org.thunderpay.billing.payment.api.InvoicePaymentApi;
import org.thunderpay.billing.invoice.api.InvoiceUserApi;
import org.thunderpay.billing.osgi.api.config.PluginConfigServiceApi;
import org.thunderpay.billing.overdue.api.OverdueApi;
import org.thunderpay.billing.payment.api.AdminPaymentApi;
import org.thunderpay.billing.payment.api.PaymentApi;
import org.thunderpay.billing.security.api.SecurityApi;
import org.thunderpay.billing.tenant.api.TenantUserApi;
import org.thunderpay.billing.usage.api.UsageUserApi;
import org.thunderpay.billing.util.api.AuditUserApi;
import org.thunderpay.billing.util.api.CustomFieldUserApi;
import org.thunderpay.billing.util.api.ExportUserApi;
import org.thunderpay.billing.util.api.RecordIdApi;
import org.thunderpay.billing.util.api.TagUserApi;
import org.thunderpay.billing.util.nodes.ThunderpayNodesApi;

public interface OSGIThunderpay {

    public AccountUserApi getAccountUserApi();

    public CatalogUserApi getCatalogUserApi();

    public SubscriptionApi getSubscriptionApi();

    public InvoicePaymentApi getInvoicePaymentApi();

    public InvoiceUserApi getInvoiceUserApi();

    public PaymentApi getPaymentApi();

    public TenantUserApi getTenantUserApi();

    public UsageUserApi getUsageUserApi();

    public AuditUserApi getAuditUserApi();

    public CustomFieldUserApi getCustomFieldUserApi();

    public ExportUserApi getExportUserApi();

    public TagUserApi getTagUserApi();

    public EntitlementApi getEntitlementApi();

    public RecordIdApi getRecordIdApi();

    public CurrencyConversionApi getCurrencyConversionApi();

    public OverdueApi getOverdueApi();

    public PluginConfigServiceApi getPluginConfigServiceApi();

    public SecurityApi getSecurityApi();

    public PluginsInfoApi getPluginsInfoApi();

    public ThunderpayNodesApi getThunderpayNodesApi();

    public AdminPaymentApi getAdminPaymentApi();
}