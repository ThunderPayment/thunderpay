/**
 * @file OverdueApiException.java
 * @author Krisna Pranav
 * @brief Overdue Api Exception
 * @version 1.0
 * @date 2025-03-19
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.overdue.api;

import org.thunderpay.billing.BillingExceptionBase;
import org.thunderpay.billing.ErrorCode;

public class OverdueApiException extends BillingExceptionBase {

    public OverdueApiException(final BillingExceptionBase cause) {
        super(cause);
    }

    public OverdueApiException(final Throwable cause, final int code, final String msg) {
        super(cause, code, msg);
    }

    private static final long serialVersionUID = 1L;

    public OverdueApiException(final Throwable cause, final ErrorCode code, final Object... args) {
        super(cause, code, args);
    }

    public OverdueApiException(final ErrorCode code, final Object... args) {
        super(code, args);
    }
}