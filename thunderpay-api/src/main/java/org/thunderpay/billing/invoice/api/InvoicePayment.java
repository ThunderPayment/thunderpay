/**
 * @file InvoicePayment
 * @author Krisna Pranav
 * @brief Invoice Payment
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.invoice.api;

import java.math.BigDecimal;
import java.util.UUID;
import org.joda.time.DateTime;
import org.thunderpay.billing.catalog.api.Currency;
import org.thunderpay.billing.util.entity.Entity;

public interface InvoicePayment extends Entity {
    UUID getPaymentId();

    InvoicePaymentType getType();

    UUID getInvoiceId();

    DateTime getPaymentDate();

    BigDecimal getAmount();

    Currency getCurrency();

    UUID getLinkedInvoicePaymentId();

    String getPaymentCookieId();

    Currency getProcessedCurrency();

    InvoicePaymentStatus getStatus();

}