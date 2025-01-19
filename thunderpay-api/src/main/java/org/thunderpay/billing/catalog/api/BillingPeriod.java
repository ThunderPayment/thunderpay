/**
 * @file BillingPeriod.java
 * @author Krisna Pranav
 * @brief Billing Period
 * @version 1.0
 * @date 2025-01-19
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.catalog.api;

import org.joda.time.Period;

public enum BillingPeriod {
    DAILY(Period.days(1)),
    WEEKLY(Period.weeks(1)),
    BIWEEKLY(Period.weeks(2)),
    THIRTY_DAYS(Period.days(30)),
    THIRTY_ONE_DAYS(Period.days(31)),
    SIXTY_DAYS(Period.days(60)),
    NINETY_DAYS(Period.days(90)),
    MONTHLY(Period.months(1)),
    BIMESTRIAL(Period.months(2)),
    QUARTERLY(Period.months(3)),
    TRIANNUAL(Period.months(4)),
    BIANNUAL(Period.months(6)),
    ANNUAL(Period.years(1)),
    SESQUIENNIAL(Period.months(18)),
    BIENNIAL(Period.years(2)),
    TRIENNIAL(Period.years(3)),
    NO_BILLING_PERIOD(Period.ZERO);

    private final Period period;

    BillingPeriod(final Period period) {
        this.period = period;
    }

    public Period getPeriod() {
        return period;
    }
}
