/**
 * @file PlanPhase.java
 * @author Krisna Pranav
 * @brief Plan Phase
 * @version 1.0
 * @date 2025-01-30
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 */

package org.thunderpay.billing.catalog.api;

import java.math.BigDecimal;

public interface PlanPhase extends CatalogEntity {
    public StaticCatalog getCatalog();

    public Fixed getFixed();

    public Recurring getRecurring();
    
    public Usage[] getUsages();

    public boolean compliesWithLimits(String unit, BigDecimal value);
}
