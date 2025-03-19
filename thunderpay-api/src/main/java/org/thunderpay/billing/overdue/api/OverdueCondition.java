/**
 * @file OverdueCondition.java
 * @author Krisna Pranav
 * @brief Overdue Condition
 * @version 1.0
 * @date 2025-03-19
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.overdue.api;

import java.math.BigDecimal;
import org.thunderpay.billing.catalog.api.Duration;
import org.thunderpay.billing.payment.api.PaymentResponse;
import org.thunderpay.billing.util.tag.ControlTagType;

public interface OverdueCondition {

    public Integer getNumberOfUnpaidInvoicesEqualsOrExceeds();

    public BigDecimal getTotalUnpaidInvoiceBalanceEqualsOrExceeds();

    public Duration getTimeSinceEarliestUnpaidInvoiceEqualsOrExceeds();

    public PaymentResponse [] getResponseForLastFailedPaymentIn();

    public ControlTagType getInclusionControlTagType();

    public ControlTagType getExclusionControlTagType();
}