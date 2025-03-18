/**
 * @file CustomFieldApiException.java
 * @author Krisna Pranav
 * @brief Custom Field API Exception
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.util.api;

import org.thunderpay.billing.BillingExceptionBase;
import org.thunderpay.billing.ErrorCode;

public class CustomFieldApiException extends BillingExceptionBase {

    private static final long serialVersionUID = 1L;

    public CustomFieldApiException(final Throwable cause, final int code, final String msg) {
        super(cause, code, msg);
    }

    public CustomFieldApiException(final Throwable cause, final ErrorCode code, final Object... args) {
        super(cause, code, args);
    }

    public CustomFieldApiException(final ErrorCode code, final Object... args) {
        super(code, args);
    }
}