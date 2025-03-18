package org.thunderpay.billing.catalog.api;

import java.math.BigDecimal;
import java.util.Collection;

public interface Product extends CatalogEntity {

    public StaticCatalog getCatalog();

    public Collection<Product> getAvailable();

    public Collection<Product> getIncluded();

    public ProductCategory getCategory();

    public String getCatalogName();

    public Limit[] getLimits();

    public boolean compliesWithLimits(String unit, BigDecimal value);

}