/**
 * @file AccountApiException.java
 * @author Krisna Pranav
 * @brief Account Api Exceptions
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.account.api;

import org.thunderpay.billing.BillingExceptionBase;
import org.thunderpay.billing.ErrorCode;

public class AccountApiException extends BillingExceptionBase {

    private static final long serialVersionUID = 1L;

    public AccountApiException(final Throwable cause, final int code, final String msg) {
        super(cause, code, msg);
    }

    public AccountApiException(final Throwable cause, final ErrorCode code, final Object... args) {
        super(cause, code, args);
    }

    public AccountApiException(final ErrorCode code, final Object... args) {
        super(code, args);
    }
}