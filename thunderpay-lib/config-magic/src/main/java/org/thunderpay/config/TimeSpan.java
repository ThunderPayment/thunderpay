/**
 * @file TimeSpan.java
 * @author Krisna Pranav
 * @brief Time Span
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.config;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeSpan {
    private static final Pattern SPLIT = Pattern.compile("^(\\d+)\\s?(\\w+)$");
    private static final HashMap<String, TimeUnit> UNITS = new HashMap<String, TimeUnit>();

    static {
        UNITS.put("ms", TimeUnit.MILLISECONDS);
        UNITS.put("millisecond", TimeUnit.MILLISECONDS);
        UNITS.put("milliseconds", TimeUnit.MILLISECONDS);
        UNITS.put("s", TimeUnit.SECONDS);
        UNITS.put("second", TimeUnit.SECONDS);
        UNITS.put("seconds", TimeUnit.SECONDS);
        UNITS.put("m", TimeUnit.MINUTES);
        UNITS.put("min", TimeUnit.MINUTES);
        UNITS.put("minute", TimeUnit.MINUTES);
        UNITS.put("minutes", TimeUnit.MINUTES);
        UNITS.put("h", TimeUnit.HOURS);
        UNITS.put("hour", TimeUnit.HOURS);
        UNITS.put("hours", TimeUnit.HOURS);
        UNITS.put("d", TimeUnit.DAYS);
        UNITS.put("day", TimeUnit.DAYS);
        UNITS.put("days", TimeUnit.DAYS);
    }

    private final long period;
    private final TimeUnit unit;
    private final long millis;
}
