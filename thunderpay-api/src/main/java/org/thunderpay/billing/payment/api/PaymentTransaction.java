/**
 * @file PaymentTransaction.java
 * @author Krisna Pranav
 * @brief Payment Transaction
 * @version 1.0
 * @date 2025-03-19
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.payment.api;

import java.math.BigDecimal;
import java.util.UUID;
import org.joda.time.DateTime;
import org.thunderpay.billing.catalog.api.Currency;
import org.thunderpay.billing.payment.plugin.api.PaymentTransactionInfoPlugin;
import org.thunderpay.billing.util.entity.Entity;

public interface PaymentTransaction extends Entity {
    UUID getPaymentId();

    String getExternalKey();

    TransactionType getTransactionType();

    DateTime getEffectiveDate();

    BigDecimal getAmount();

    Currency getCurrency();

    BigDecimal getProcessedAmount();

    Currency getProcessedCurrency();

    String getGatewayErrorCode();

    String getGatewayErrorMsg();

    TransactionStatus getTransactionStatus();

    PaymentTransactionInfoPlugin getPaymentInfoPlugin();
}