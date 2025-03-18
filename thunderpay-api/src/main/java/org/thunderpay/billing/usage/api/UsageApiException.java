/**
 * @file UsageApiException
 * @author Krisna Pranav
 * @brief Usage Api Exception
 * @version 1.0
 * @date 2025-03-18
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.usage.api;

import org.thunderpay.billing.BillingExceptionBase;
import org.thunderpay.billing.ErrorCode;

public class UsageApiException extends BillingExceptionBase {

    private static final long serialVersionUID = 1L;

    public UsageApiException(final Throwable cause, final int code, final String msg) {
        super(cause, code, msg);
    }

    public UsageApiException(final Throwable cause, final ErrorCode code, final Object... args) {
        super(cause, code, args);
    }

    public UsageApiException(final ErrorCode code, final Object... args) {
        super(code, args);
    }
}