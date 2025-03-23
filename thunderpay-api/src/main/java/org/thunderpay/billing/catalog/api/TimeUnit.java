/**
 * @file TimeUnit.java
 * @author Krisna Pranav
 * @brief Time Unit
 * @version 1.0
 * @date 2025-01-23
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.catalog.api;

import jakarta.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum TimeUnit {
    DAYS,
    WEEKS,
    MONTHS,
    YEARS,
    UNLIMITED
}