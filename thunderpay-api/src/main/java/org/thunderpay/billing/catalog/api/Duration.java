/**
 * @file Duration.java
 * @author Krisna Pranav
 * @brief Duration
 * @version 1.0
 * @date 2025-01-21
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */


package org.thunderpay.billing.catalog.api;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.Period;

public interface Duration {
    public TimeUnit getUnit();

    public int getNumber();

    public DateTime addToDateTime(DateTime dateTime) throws CatalogApiException;

    public LocalDate addToLocalDate(LocalDate localDate) throws CatalogApiException;

    public Period toJodaPeriod();
}