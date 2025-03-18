/**
 * @file CasePriceList.java
 * @author Krisna Pranav
 * @brief Case Price List
 * @version 1.0
 * @date 2025-03-18
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.catalog.api.rules;

import org.thunderpay.billing.catalog.api.PriceList;

public interface CasePriceList extends Case{
    public PriceList getDestinationPriceList();
}