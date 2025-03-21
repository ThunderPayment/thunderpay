/**
 * @file PaymentApiException.java
 * @author Krisna Pranav
 * @brief Payment Api Exception
 * @version 1.0
 * @date 2025-03-19
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.payment.api;

import org.thunderpay.billing.BillingExceptionBase;
import org.thunderpay.billing.ErrorCode;
import org.thunderpay.billing.account.api.AccountApiException;
import org.thunderpay.billing.invoice.api.InvoiceApiException;

public class PaymentApiException extends BillingExceptionBase {

    private static final long serialVersionUID = 39445033L;

    public PaymentApiException(final InvoiceApiException e) {
        super(e, e.getCode(), e.getMessage());
    }

    public PaymentApiException(final AccountApiException e) {
        super(e, e.getCode(), e.getMessage());
    }

    public PaymentApiException(final Throwable e, final ErrorCode code, final Object... args) {
        super(e, code, args);
    }

    public PaymentApiException(final Throwable e, final int code, final String message) {
        super(e, code, message);
    }

    public PaymentApiException(final ErrorCode code, final Object... args) {
        super(code, args);
    }
}