/**
 * @file CurrencyConversionException.java
 * @author Krisna Pranav
 * @brief Currency Conversion Exception
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.currency.api;

import org.thunderpay.billing.BillingExceptionBase;
import org.thunderpay.billing.ErrorCode;

public class CurrencyConversionException extends BillingExceptionBase {

    public CurrencyConversionException(final Throwable cause, final int code, final String msg) {
        super(cause, code, msg);
    }

    public CurrencyConversionException(final BillingExceptionBase cause) {
        super(cause);
    }

    public CurrencyConversionException(final Throwable cause, final ErrorCode code, final Object... args) {
        super(cause, code, args);
    }

    public CurrencyConversionException(final ErrorCode code, final Object... args) {
        super(code, args);
    }
}