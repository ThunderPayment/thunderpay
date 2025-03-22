/**
 * @file TenantApiException.java
 * @author Krisna Pranav
 * @brief Tenant Api Exception
 * @version 1.0
 * @date 2025-03-22
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.tenant.api;

import org.thunderpay.billing.BillingExceptionBase;
import org.thunderpay.billing.ErrorCode;

public class TenantApiException extends BillingExceptionBase {

    private static final long serialVersionUID = 1L;

    public TenantApiException(final Throwable cause, final int code, final String msg) {
        super(cause, code, msg);
    }

    public TenantApiException(final Throwable cause, final ErrorCode code, final Object... args) {
        super(cause, code, args);
    }

    public TenantApiException(final ErrorCode code, final Object... args) {
        super(code, args);
    }
}