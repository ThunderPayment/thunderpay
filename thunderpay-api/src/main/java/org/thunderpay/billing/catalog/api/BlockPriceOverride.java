/**
 * @file BlockPriceOverride.java
 * @author Krisna Pranav
 * @brief Block Price Override
 * @version 1.0
 * @date 2025-01-21
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.catalog.api;

import java.math.BigDecimal;

public interface BlockPriceOverride {
    public String getUnitName();

    public BigDecimal getSize();

    public BigDecimal getPrice();
}
