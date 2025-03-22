/**
 * @file PaymentAttempt.java
 * @author Krisna Pranav
 * @brief Payment Attempt
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
import org.thunderpay.billing.catalog.api.Currency;
import org.thunderpay.billing.util.entity.Entity;

public interface PaymentAttempt extends Entity {

    /**
     * @return the id of the account.
     */
    UUID getAccountId();

    /**
     * @return the id of the payment method.
     */
    UUID getPaymentMethodId();

    String getPaymentExternalKey();

    /**
     * @return the id of the transaction.
     */
    UUID getTransactionId();

    /**
     * @return the external key of the transaction.
     */
    String getTransactionExternalKey();

    /**
     * @return the type of the transaction.
     */
    TransactionType getTransactionType();

    /**
     * @return the effective date.
     */
    DateTime getEffectiveDate();

    /**
     * @return the name of the state.
     */
    String getStateName();

    BigDecimal getAmount();

    Currency getCurrency();

    String getPluginName();

    List<PluginProperty> getPluginProperties();


}