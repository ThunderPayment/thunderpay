/**
 * @file Tier.java
 * @author Krisna Pranav
 * @brief Tier
 * @version 1.0
 * @date 2025-01-21
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.catalog.api;

public interface Tier {
    public Limit[] getLimits();

    public TieredBlock[] getTieredBlocks();

    public InternationalPrice getFixedPrice();

    public InternationalPrice getRecurringPrice();
}
