/**
 * @file Block.java
 * @author Krisna Pranav
 * @brief Block
 * @version 1.0
 * @date 2025-01-20
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.catalog.api;

import java.math.BigDecimal;

public interface Block {
    public BlockType getType();

    public Unit getUnit();

    public BigDecimal getSize();

    public InternationalPrice getPrice();

    public BigDecimal getMinTopUpCredit() throws CatalogApiException;
}
