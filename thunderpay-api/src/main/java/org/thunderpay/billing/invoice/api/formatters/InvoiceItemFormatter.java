/**
 * @file InvoiceItemFormatter.java
 * @author Krisna Pranav
 * @brief Invoice Item Formatter
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.invoice.api.formatters;

import org.thunderpay.billing.invoice.api.InvoiceItem;

public interface InvoiceItemFormatter extends InvoiceItem {

    public String getFormattedStartDate();

    public String getFormattedEndDate();

    public String getFormattedAmount();
}