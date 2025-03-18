package org.thunderpay.billing.catalog.api;

public interface Recurring {
    public BillingPeriod getBillingPeriod();

    public InternationalPrice getRecurringPrice();
}