/**
 * @file MigrationPlan.java
 * @author Krisna Pranav
 * @brief Migration Plan
 * @version 1.0
 * @date 2025-01-30
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.catalog.api;

public interface MigrationPlan extends Plan {
    public static final String MIGRATION_PLAN_NAME = "__THUNDERPAY_MIGRATION_PLAN__";
    public static final String MIGRATION_PLAN_PHASE_NAME = "__THUNDERPAY_MIGRATION_PLAN_PHASE__";
}