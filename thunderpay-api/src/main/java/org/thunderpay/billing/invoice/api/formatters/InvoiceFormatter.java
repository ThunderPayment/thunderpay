/**
 * @file InvoiceFormatter.java
 * @author Krisna Pranav
 * @brief Invoice Formatter
 * @version 1.0
 * @date 2025-03-19
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.invoice.api.formatters;

import org.thunderpay.billing.catalog.api.Currency;
import org.thunderpay.billing.invoice.api.Invoice;

public interface InvoiceFormatter extends Invoice {

    public String getFormattedInvoiceDate();

    public String getFormattedChargedAmount();

    public String getFormattedPaidAmount();

    public String getFormattedBalance();

    public Currency getProcessedCurrency();

    public String getProcessedPaymentRate();
}