/**
 * @file Listing.java
 * @author Krisna Pranav
 * @brief Listing
 * @version 1.0
 * @date 2025-01-30
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.catalog.api;

public interface Listing {
    Plan getPlan();

    PriceList getPriceList();
}
