/**
 * @file SubscriptionEvent.java
 * @author Krisna Pranav
 * @version Subscription Event
 * @date 2025-01-19
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.entitlement.api;

import java.util.UUID;

import org.joda.time.DateTime;
import org.thunderpay.billing.catalog.api.BillingPeriod;
import org.thunderpay.billing.catalog.api.Plan;
import org.thunderpay.billing.catalog.api.PlanPhase;
import org.thunderpay.billing.catalog.api.PriceList;
import org.thunderpay.billing.catalog.api.Product;

public interface SubscriptionEvent {
    public UUID getId();

    public UUID getEntitlementId();

    public DateTime getEffectiveDate();

    public SubscriptionEventType getSubscriptionEventType();

    public boolean isBlockedBilling();

    public boolean isBlockedEntitlement();

    public String getServiceName();

    public String getServiceStateName();

    public Product getPrevProduct();

    public Plan getPrevPlan();

    public PlanPhase getPrevPhase();

    public PriceList getPrevPriceList();

    public BillingPeriod getPrevBillingPeriod();

    public Product getNextProduct();

    public Plan getNextPlan();

    public PlanPhase getNextPhase();

    public PriceList getNextPriceList();

    public BillingPeriod getNextBillingPeriod();

}