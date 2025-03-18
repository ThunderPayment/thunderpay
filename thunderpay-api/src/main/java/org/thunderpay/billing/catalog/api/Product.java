package org.thunderpay.billing.catalog.api;

import java.math.BigDecimal;

public class Product {
    public StaticCatalog getCatalog();

    public String getCatalogName();

    public Collection<Product> getIncluded();

    public Limit[] getLimits();

    public boolean compilesWithLimit(String unit, BigDecimal value);
}
