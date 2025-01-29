/**
 * @file CurrencyValueNull.java
 * @author Krisna Pranav
 * @brief Currency Value Null
 * @version 1.0
 * @date 2025-01-21
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.catalog.api;

import org.thunderpay.billing.ErrorCode;

public class CurrencyValueNull extends CatalogApiException {

    private static final long serialVersionUID = 1L;

    public CurrencyValueNull(final Throwable cause, final Object... args) {
        super(cause, ErrorCode.CAT_PRICE_VALUE_NULL_FOR_CURRENCY, args);
    }

    public CurrencyValueNull(final Object... args) {
        super(ErrorCode.CAT_PRICE_VALUE_NULL_FOR_CURRENCY, args);
    }
}