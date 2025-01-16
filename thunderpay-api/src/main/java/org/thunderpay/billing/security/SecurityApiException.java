/**
 * @file SecurityApiException.java
 * @author Krisna Pranav
 * @brief Security API Exception
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.security;

import org.thunderpay.billing.BillingExceptionBase;
import org.thunderpay.billing.ErrorCode;

public class SecurityApiException extends BillingExceptionBase {
    public SecurityApiException(final Throwable cause, final int code, final String msg) {
        super(cause, code, msg);
    }

    public SecurityApiException(final Throwable cause, final ErrorCode code, final Object... args) {
        super(cause, code, args);
    }
}
