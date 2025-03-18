/**
 * @file RolledUpUnit.java
 * @author Krisna Pranav
 * @brief Rolled Up Unit 
 * @version 1.0
 * @date 2025-03-18
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.usage.api;

import java.math.BigDecimal;

public interface RolledUpUnit {

    public String getUnitType();

    public BigDecimal getAmount();
}