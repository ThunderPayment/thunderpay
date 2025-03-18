/**
 * @file Plan.java
 * @author Krisna Pranav
 * @brief Phase Type
 * @version 1.0
 * @date 2025-03-18
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 */

package org.thunderpay.billing.catalog.api;

import java.util.Date;
import java.util.Iterator;

import org.joda.time.DateTime;

public interface Plan extends CatalogEntity {

    public StaticCatalog getCatalog();

    /**
     * @return the recurring billing mode for this catalog
     */
    public BillingMode getRecurringBillingMode();

    /**
     * @return an array of {@code PlanPhase}
     */
    public PlanPhase[] getInitialPhases();

    /**
     * @return the {@code Product} associated with that {@code Plan}
     */
    public Product getProduct();

    /**
     *
     * @return the {@PriceList} in which that {@code Plan} is defined
     */
    public PriceList getPriceList();

    public Iterator<PlanPhase> getInitialPhaseIterator();

    public PlanPhase getFinalPhase();

    public BillingPeriod getRecurringBillingPeriod();

    public int getPlansAllowedInBundle();

    public PlanPhase[] getAllPhases();

    public Date getEffectiveDateForExistingSubscriptions();

    public PlanPhase findPhase(String name) throws CatalogApiException;

    public DateTime dateOfFirstRecurringNonZeroCharge(DateTime subscriptionStartDate, PhaseType intialPhaseType);

}