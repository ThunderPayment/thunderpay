/**
 * @file TieredBlock.java
 * @author Krisna Pranav
 * @brief Tiered Block
 * @version 1.0
 * @date 2025-01-21
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.catalog.api;

import java.math.BigDecimal;

public interface TieredBlock extends Block {
    public BigDecimal getMax();
}
