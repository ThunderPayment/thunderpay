/**
 * @file DataAmount.java
 * @author Krisna Pranav
 * @brief Data Amount
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.config;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataAmount {
    private static final Pattern SPLIT = Pattern.compile("^(\\d+)\\s*([a-zA-Z]+)$");
    private static final Pattern NUM_ONLY = Pattern.compile("^(\\d+)$");
    private final long value;
    private final DataAmountUnit unit;
    private final long numBytes;

    public DataAmount(final String spec) {
        Matcher m = SPLIT.matcher(spec);

        if (!m.matches()) {
            m = NUM_ONLY.matcher(spec);

            if (!m.matches()) {
                throw new IllegalArgumentException(String.format("%s is not a valid data amount", spec));
            }

            unit = DataAmountUnit.BYTE;
            value = numBytes = Long.parseLong(spec);
        } else {
            final String number = m.group(1);
            final String type = m.group(2);

            this.value = Long.parseLong(number);
            this.unit = DataAmountUnit.fromString(type);
            this.numBytes = unit.getFactor() * value;
        }
    }

    public DataAmount(final long value, final DataAmountUnit unit) {
        this.value = value;
        this.unit = unit;
        this.numBytes = unit.getFactor() * value;
    }
}
