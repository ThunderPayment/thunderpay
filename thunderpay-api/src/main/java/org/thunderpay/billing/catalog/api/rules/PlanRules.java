/**
 * @file PlanRules.java
 * @author Krisna Pranav
 * @brief Plan Rules
 * @version 1.0
 * @date 2025-03-18
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.catalog.api.rules;


import org.thunderpay.billing.catalog.api.BillingActionPolicy;
import org.thunderpay.billing.catalog.api.BillingAlignment;
import org.thunderpay.billing.catalog.api.CatalogApiException;
import org.thunderpay.billing.catalog.api.PlanAlignmentCreate;
import org.thunderpay.billing.catalog.api.PlanChangeResult;
import org.thunderpay.billing.catalog.api.PlanPhaseSpecifier;
import org.thunderpay.billing.catalog.api.PlanSpecifier;
import org.thunderpay.billing.catalog.api.StaticCatalog;

public interface PlanRules {

    public StaticCatalog getCatalog();

    public Iterable<CaseChangePlanPolicy> getCaseChangePlanPolicy();

    public Iterable<CaseChangePlanAlignment> getCaseChangePlanAlignment();

    public Iterable<CaseCancelPolicy> getCaseCancelPolicy();

    public Iterable<CaseCreateAlignment> getCaseCreateAlignment();

    public Iterable<CaseBillingAlignment> getCaseBillingAlignment();

    public Iterable<CasePriceList> getCasePriceList();

    public PlanAlignmentCreate getPlanCreateAlignment(final PlanSpecifier specifier) throws CatalogApiException;

    public BillingActionPolicy getPlanCancelPolicy(final PlanPhaseSpecifier planPhase) throws CatalogApiException;

    public BillingAlignment getBillingAlignment(final PlanPhaseSpecifier planPhase) throws CatalogApiException;

    public PlanChangeResult getPlanChangeResult(final PlanPhaseSpecifier from, final PlanSpecifier to) throws CatalogApiException;

}