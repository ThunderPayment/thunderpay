/**
 * @file TagApiException.java
 * @author Krisna Pranav
 * @brief Tag Api Exception
 * @version 1.0
 * @date 2025-03-19
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.util.api;

import org.thunderpay.billing.BillingExceptionBase;
import org.thunderpay.billing.ErrorCode;

public class TagApiException extends BillingExceptionBase {

    private static final long serialVersionUID = 1L;

    public TagApiException(final Throwable cause, final int code, final String msg) {
        super(cause, code, msg);
    }

    public TagApiException(final Throwable cause, final ErrorCode code, final Object... args) {
        super(cause, code, args);
    }

    public TagApiException(final ErrorCode code, final Object... args) {
        super(code, args);
    }
}