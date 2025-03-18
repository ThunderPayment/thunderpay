/**
 * @file StaticCatalog.java
 * @author Krisna Pranav
 * @brief Static Catalog
 * @version 1.0
 * @date 2025-03-18
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 */

package org.thunderpay.billing.catalog.api;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.thunderpay.billing.catalog.api.rules.PlanRules;

public interface StaticCatalog {

    public String getCatalogName();

    public Date getEffectiveDate();

    public Currency[] getSupportedCurrencies();

    public Collection<Product> getProducts();

    public Unit[] getUnits();

    public Collection<Plan> getPlans();

    public PlanRules getPlanRules();

    public Plan createOrFindPlan(PlanSpecifier spec, PlanPhasePriceOverridesWithCallContext overrides) throws CatalogApiException;

    public Plan findPlan(String name) throws CatalogApiException;

    public Product findProduct(String name) throws CatalogApiException;

    public PlanPhase findPhase(String name) throws CatalogApiException;

    public PriceListSet getPriceLists() throws CatalogApiException;

    public PriceList findPriceList(String name) throws CatalogApiException;

    public List<Listing> getAvailableBasePlanListings();

    public List<Listing> getAvailableAddOnListings(String baseProductName, String priceListName);

}