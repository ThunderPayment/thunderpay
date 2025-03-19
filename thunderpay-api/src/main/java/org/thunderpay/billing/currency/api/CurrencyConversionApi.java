/**
 * @file CurrencyConversionApi.java
 * @author Krisna Pranav
 * @brief Currency Conversion Api
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.currency.api;

import java.util.Set;
import org.joda.time.DateTime;
import org.thunderpay.billing.ThunderApi;
import org.thunderpay.billing.catalog.api.Currency;

public interface CurrencyConversionApi extends ThunderApi {
    public Set<Currency> getBaseRates()
            throws CurrencyConversionException;

    public CurrencyConversion getCurrentCurrencyConversion(Currency baseCurrency)
            throws CurrencyConversionException;

    public CurrencyConversion getCurrencyConversion(Currency baseCurrency, DateTime dateConversion)
            throws CurrencyConversionException;
}