/**
 * @file Usage.java
 * @author Krisna Pranav
 * @brief Usage
 * @version 1.0
 * @date 2025-01-23
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.catalog.api;

import java.math.BigDecimal;

public interface Usage extends CatalogEntity {
    public StaticCatalog getCatalog();

    public BillingMode getBillingMode();

    public UsageType getUsageType();

    public TierBlockPolicy getTierBlockPolicy();

    public BillingPeriod getBillingPeriod();

    public boolean compilesWithLimits(String unit, BigDecimal value);

    public Limit[] getLimits();

    public Tier[] getTiers();

}
