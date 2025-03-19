/**
 * @file InvoiceApiException.java
 * @author Krisna Pranav
 * @brief Invoice Api Exception
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.invoice.api;

import org.thunderpay.billing.BillingExceptionBase;
import org.thunderpay.billing.ErrorCode;

public class InvoiceApiException extends BillingExceptionBase {

    public InvoiceApiException(final BillingExceptionBase cause) {
        super(cause);
    }

    public InvoiceApiException(final Throwable cause, final int code, final String msg) {
        super(cause, code, msg);
    }

    public InvoiceApiException(final Throwable cause, final ErrorCode code, final Object... args) {
        super(cause, code, args);
    }

    public InvoiceApiException(final ErrorCode code, final Object... args) {
        super(code, args);
    }
}
