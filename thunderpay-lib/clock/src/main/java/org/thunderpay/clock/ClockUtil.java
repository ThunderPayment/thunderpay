/**
 * @file ClockUtil.java
 * @author Krisna Pranav
 * @brief ClockUtil
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.clock;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.IllegalInstantException;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

public class ClockUtil {

    public static DateTime toUTCDateTime(final LocalDate localDate, final LocalTime referenceTime, final DateTimeZone dateTimeZone) {
        DateTime targetDateTime;
        try {
            targetDateTime = new DateTime(localDate.getYear(),
                    localDate.getMonthOfYear(),
                    localDate.getDayOfMonth(),
                    referenceTime.getHourOfDay(),
                    referenceTime.getMinuteOfHour(),
                    referenceTime.getSecondOfMinute(),
                    dateTimeZone);
        } catch (final IllegalInstantException e) {
            targetDateTime = localDate.toDateTimeAtStartOfDay(dateTimeZone);
        }

        return toUTCDateTime(targetDateTime);
    }

    public static LocalDate toLocalDate(final DateTime dateTime, final DateTimeZone dateTimeZone) {
        return new LocalDate(dateTime, dateTimeZone);
    }

    public static DateTime toUTCDateTime(final DateTime dateTime) {
        return toDateTime(dateTime, DateTimeZone.UTC);
    }

    public static DateTime toDateTime(final DateTime dateTime, final DateTimeZone accountTimeZone) {
        return dateTime.toDateTime(accountTimeZone);
    }
}
