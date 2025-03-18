package org.thunderpay.billing.catalog.api;

import java.math.BigDecimal;
import java.util.List;

public interface SimplePlanDescriptor {
    String getPlanId();

    String getProductName();

    ProductCategory getProductCategory();

    List<String> getAvailableBaseProducts();

    Currency getCurrency();

    BigDecimal getAmount();

    BillingPeriod getBillingPeriod();

    Integer getTrialLength();

    TimeUnit getTrialTimeUnit();
}