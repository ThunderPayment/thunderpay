package org.thunderpay.billing.payment.api;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import org.thunderpay.billing.catalog.api.Currency;
import org.thunderpay.billing.util.entity.Entity;

public interface Payment extends Entity {
    UUID getAccountId();

    UUID getPaymentMethodId();

    Integer getPaymentNumber();

    String getExternalKey();

    BigDecimal getAuthAmount();

    BigDecimal getCapturedAmount();

    BigDecimal getPurchasedAmount();

    /**
     * @return the credited amount
     */
    BigDecimal getCreditedAmount();

    /**
     * @return the refunded amount
     */
    BigDecimal getRefundedAmount();

    /**
     * @return true if there was a void operation following an authorization
     */
    Boolean isAuthVoided();

    /**
     * @return the currency associated with that payment
     */
    Currency getCurrency();

    /**
     * @return the list of attempts on that payment
     */
    List<PaymentTransaction> getTransactions();

    /**
     * @return the list of Payment attempts (past and future)
     */
    List<PaymentAttempt> getPaymentAttempts();
}