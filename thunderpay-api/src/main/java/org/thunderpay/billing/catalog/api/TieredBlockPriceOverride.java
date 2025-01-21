/**
 * @file TieredBlockPriceOverride
 * @author Krisna Pranav
 * @brief Tiered Block Price Override
 * @version 1.0
 * @date 2025-01-21
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.catalog.api;

import java.math.BigDecimal;

public interface TieredBlockPriceOverride extends BlockPriceOverride {
    public  BigDecimal getMax();
}
