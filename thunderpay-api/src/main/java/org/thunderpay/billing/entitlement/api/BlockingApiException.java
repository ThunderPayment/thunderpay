package org.thunderpay.billing.entitlement.api;

import org.thunderpay.billing.BillingExceptionBase;
import org.thunderpay.billing.ErrorCode;

public class BlockingApiException extends BillingExceptionBase {
    private static final long serialVersionUID = 1L;

    public BlockingApiException(final Throwable cause, final ErrorCode code) {
        super(cause, code);
    }
}