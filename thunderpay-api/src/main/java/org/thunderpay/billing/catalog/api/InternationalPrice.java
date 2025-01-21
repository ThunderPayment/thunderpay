/**
 * @file InternationalPrice.java
 * @author Krisna Pranav
 * @brief Internation Price
 * @version 1.0
 * @date 2025-01-21
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.catalog.api;

import java.math.BigDecimal;

public interface InternationalPrice {
    public abstract Price[] getPrices();

    public abstract BigDecimal getPrice(Currency currency) throws CatalogApiException;

    public abstract boolean isZero();
}