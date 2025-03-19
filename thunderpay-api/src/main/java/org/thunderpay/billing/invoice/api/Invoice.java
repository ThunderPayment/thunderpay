/**
 * @file Invoice.java
 * @author Krisna Pranav
 * @brief Invoice
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.invoice.api;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import org.joda.time.LocalDate;
import org.thunderpay.billing.catalog.api.Currency;
import org.thunderpay.billing.util.entity.Entity;

public interface Invoice extends Entity {
    boolean addInvoiceItem(InvoiceItem item);

    boolean addInvoiceItems(Collection<InvoiceItem> items);

    List<InvoiceItem> getInvoiceItems();

    List<String> getTrackingIds();

    boolean addTrackingIds(Collection<String> trackingIds);

    public <T extends InvoiceItem> List<InvoiceItem> getInvoiceItems(Class<T> clazz);

    int getNumberOfItems();

    boolean addPayment(InvoicePayment payment);

    boolean addPayments(Collection<InvoicePayment> payments);

    List<InvoicePayment> getPayments();

    int getNumberOfPayments();

    UUID getAccountId();

    Integer getInvoiceNumber();

    LocalDate getInvoiceDate();

    LocalDate getTargetDate();

    Currency getCurrency();

    BigDecimal getPaidAmount();

    BigDecimal getOriginalChargedAmount();

    BigDecimal getChargedAmount();

    BigDecimal getCreditedAmount();

    BigDecimal getRefundedAmount();

    BigDecimal getBalance();

    boolean isMigrationInvoice();

    InvoiceStatus getStatus();

    boolean isParentInvoice();

    UUID getParentAccountId();

    UUID getParentInvoiceId();

    UUID getGroupId();
}