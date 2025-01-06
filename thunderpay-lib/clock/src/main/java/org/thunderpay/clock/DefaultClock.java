/**
 * @file DefaultClock.java
 * @author Krisna Pranav
 * @brief Default Clock
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.clock;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;

public class DefaultClock implements Clock {

    @Override
    public DateTime getNow(final DateTimeZone tz) {
        final DateTime result = new DateTime(tz);
        return truncateMs(result);
    }

    @Override
    public DateTime getUTCNow() {
        return getNow(DateTimeZone.UTC);
    }

    @Override
    public LocalDate getUTCToday() {
        return getToday(DateTimeZone.UTC);
    }

    @Override
    public LocalDate getToday(final DateTimeZone timeZone) {
        return new LocalDate(getUTCNow(), timeZone);
    }

    public static DateTime toUTCDateTime(final DateTime input) {
        if (input == null) {
            return null;
        }
        final DateTime result = input.toDateTime(DateTimeZone.UTC);
        return truncateMs(result);
    }

    public static DateTime truncateMs(final DateTime input) {
        return input.minus(input.getMillisOfSecond());
    }
}
