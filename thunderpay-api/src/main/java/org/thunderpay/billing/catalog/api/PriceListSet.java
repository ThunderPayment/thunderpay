/**
 * @file PriceListSet.java
 * @author Krisna Pranav
 * @brief Price List Set
 * @version 1.0
 * @date 2025-01-30
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 */

package org.thunderpay.billing.catalog.api;

import java.util.List;

public interface PriceListSet {
    public static final String DEFAULT_PRICELIST_NAME = "DEFAULT";

    public List<PriceList> getAllPriceLists();
}
