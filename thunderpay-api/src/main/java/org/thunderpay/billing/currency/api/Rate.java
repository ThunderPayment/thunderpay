/**
 * @file Rate.java
 * @author Krisna Pranav
 * @brief Rate
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.currency.api;

import java.math.BigDecimal;
import org.joda.time.DateTime;
import org.thunderpay.billing.catalog.api.Currency;

public interface Rate {

    public Currency getBaseCurrency();

    public Currency getCurrency();

    public BigDecimal getValue();

    public DateTime getConversionDate();
}