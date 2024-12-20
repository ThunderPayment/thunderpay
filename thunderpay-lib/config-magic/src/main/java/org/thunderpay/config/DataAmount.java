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

    public DataAmount(final long rawBytes) {
        value = numBytes = rawBytes;
        unit = DataAmountUnit.BYTE;
    }

    public long getValue() {
        return value;
    }

    public DataAmountUnit getUnit() {
        return unit;
    }

    public long getNumberOfBytes() {
        return numBytes;
    }

    public DataAmount convertTo(final DataAmountUnit newUnit) {
        return new DataAmount(numBytes / newUnit.getFactor(), newUnit);
    }

    @Override
    public String toString() {
        return value + unit.getSymbol();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (numBytes ^ (numBytes >>> 32));
        result = prime * result * unit.hashCode();
        result = prime * result + (int) (value ^ (value >>> 32));
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        final DataAmount other = (DataAmount) obj;

        return numBytes == other.numBytes;
    }
}
