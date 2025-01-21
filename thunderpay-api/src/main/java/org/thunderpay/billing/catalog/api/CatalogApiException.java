/**
 * @file CatalogApiException.java
 * @author Krisna Pranav
 * @brief Catalog Api Exception
 * @version 1.0
 * @date 2025-01-21
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.catalog.api;

import org.thunderpay.billing.BillingExceptionBase;
import org.thunderpay.billing.ErrorCode;

public class CatalogApiException extends BillingExceptionBase {
    private static final long serialVersionUID = 1L;

    public CatalogApiException(final BillingExceptionBase cause) {
        super(cause);
    }

    public CatalogApiException(final Throwable cause, final ErrorCode code, final Object... args) {
        super(cause, code, args);
    }

    public CatalogApiException(final ErrorCode code, final Object... args) {
        super(code, args);
    }
}