/**
 * @file Limit.java
 * @author Krisna Pranav
 * @brief Limit
 * @version 1.0
 * @date 2025-01-21
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.catalog.api;

import java.math.BigDecimal;

public interface Limit {
    public Unit getUnit();

    public BigDecimal getMax();

    public BigDecimal getMin();

    public boolean compilesWith(BigDecimal value);
}
