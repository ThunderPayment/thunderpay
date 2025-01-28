/**
 * @file UsagePriceOverride.java
 * @author Krisna Pranav
 * @brief Usage Price Override
 * @version 1.0
 * @date 2025-01-18
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.catalog.api;

import java.util.List;

public interface UsagePriceOverride {
    public String getName();

    public UsageType getUsageType();

    public List<TierPriceOverride> getTierPriceOverrides();
}
