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
    ///  @return DateTimeZone
    public DateTime getTime(DateTimeZone tz);

    ///  @brief: get UTC now.
    public DateTime getUTCNow();

    ///  @brief get today UTC
    public LocalDate getUTCToday();

    /// @return DateTimeZone
    public LocalDate getToday(DateTimeZone timeZone);


}