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
    public PriceList getPriceList();

    public Iterator<PlanPhase> getInitialPhaseIterator();

    public PlanPhase getFinalPhase();
    
    public int getPlansAllowedInBundle();

    public PlanPhase findPhase(String name) throws CatalogApiException;

    public DateTime dateOfFirstRecurringNonZeroCharge(DateTime subscriptionStartDate, PhaseType intiialPhase);
}