/**
 * @file PaymentTransactionInfoPlugin.java
 * @author Krisna Pranav
 * @brief Payment Transaction Info Plugin
 * @version 1.0
 * @date 2025-03-22
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.payment.plugin.api;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import org.joda.time.DateTime;
import org.thunderpay.billing.catalog.api.Currency;
import org.thunderpay.billing.payment.api.PluginProperty;
import org.thunderpay.billing.payment.api.TransactionType;

public interface PaymentTransactionInfoPlugin {
    public UUID getKbPaymentId();

    public UUID getKbTransactionPaymentId();

    public TransactionType getTransactionType();

    public BigDecimal getAmount();

    public Currency getCurrency();

    public DateTime getCreatedDate();

    public DateTime getEffectiveDate();

    public PaymentPluginStatus getStatus();

    public String getGatewayError();

    public String getGatewayErrorCode();

    public String getFirstPaymentReferenceId();

    public String getSecondPaymentReferenceId();

    public List<PluginProperty> getProperties();
}