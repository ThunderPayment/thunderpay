/**
 * @file SubscriptionApi.java
 * @author Krisna Pranav
 * @version 1.0
 * @date 2025-01-19
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.entitlement.api;

import org.thunderpay.billing.BillingExceptionBase;
import org.thunderpay.billing.ErrorCode;

public class SubscriptionApiException extends BillingExceptionBase {

    public SubscriptionApiException(final BillingExceptionBase e) {
        super(e, e.getCode(), e.getMessage());
    }

    public SubscriptionApiException(final Throwable e, final ErrorCode code, final Object... args) {
        super(e, code, args);
    }

    public SubscriptionApiException(final Throwable e, final int code, final String message) {
        super(e, code, message);
    }

    public SubscriptionApiException(final ErrorCode code, final Object... args) {
        super(code, args);
    }
}