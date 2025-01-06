/**
 * @file Clock.java
 * @author Krisna Pranav
 * @brief Clock
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

public interface Clock {
    ///  @brief: getNow
    /// @return: DateTimeZone
    public DateTime getNow(DateTimeZone tz);

    ///  @brief: getUTCNow
    ///  @return: DateTime
    public DateTime getUTCNow();

    ///  @brief: getUTCToday
    ///  @return: LocalDate
    public LocalDate getUTCToday();

    ///  @brief: getToday
    ///  @return: timeZone
    public LocalDate getToday(DateTimeZone timeZone);
}