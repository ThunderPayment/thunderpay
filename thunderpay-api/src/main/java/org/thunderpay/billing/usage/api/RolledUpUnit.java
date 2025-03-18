package org.thunderpay.billing.usage.api;

import java.math.BigDecimal;

public interface RolledUpUnit {

    public String getUnitType();

    public BigDecimal getAmount();
}