/**
 * @file CurrencyConversion.java
 * @author Krisna Pranav
 * @brief Currency Conversion
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.currency.api;

import java.util.Set;
import org.thunderpay.billing.catalog.api.Currency;

public interface CurrencyConversion {
    public Currency getBaseCurrency();
    
    public Set<Rate> getRates();
}