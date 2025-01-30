/**
 * @file Fixed.java
 * @author Krisna Pranav
 * @brief Fixed
 * @version 1.0
 * @date 2025-01-21
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.catalog.api;

public interface Fixed {
    public FixedType getType();

    public InternationalPrice getPrice();
}
