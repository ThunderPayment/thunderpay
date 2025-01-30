/**
 * @file IllegalPlanChange.java
 * @author Krisna Pranav
 * @brief Illegal Plan Change
 * @version 1.0
 * @date 2025-01-21
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.catalog.api;

import org.thunderpay.billing.ErrorCode;

public class IllegalPlanChange extends CatalogApiException {
    private static final long serialVersionUID = 1L;

    public IllegalPlanChange(final Throwable cause, final PlanPhaseSpecifier from, final PlanSpecifier to) {
        super(cause, ErrorCode.CAT_ILLEGAL_CHANGE_REQUEST, from.getProductName(), from.getBillingPeriod(), from.getPriceListName(), to.getProductName(), to.getBillingPeriod(), to.getPriceListName());
    }

    public IllegalPlanChange(final PlanPhaseSpecifier from, final PlanSpecifier to) {
        super(ErrorCode.CAT_ILLEGAL_CHANGE_REQUEST, from.getProductName(), from.getBillingPeriod(), from.getPriceListName(), to.getProductName(), to.getBillingPeriod(), to.getPriceListName());
    }
}