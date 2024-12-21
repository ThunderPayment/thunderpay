/**
 * @file DataAmountUnit.java
 * @author Krisna Pranav
 * @brief Data Amount Unit
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.config;

public enum DataAmountUnit {
    BYTE("B", 1l),

    KIBIBYTE("KiB", 1024l),
    MEBIBYTE("MiB", 1024l * 1024l),
    GIBIBYTE("GiB", 1024l * 1024l * 1024l),
    TEBIBYTE("TiB", 1024l * 1024l * 1024l * 1024l),
    PEBIBYTE("PiB", 1024l * 1024l * 1024l * 1024l * 1024l),
    EXIBYTE("EiB", 1024l * 1024l * 1024l * 1024l * 1024l * 1024l),

    KILOBYTE("kB", 1000l),
    MEGABYTE("MB", 1000l * 1000l),
    GIGABYTE("GB", 1000l * 1000l * 1000l),
    TERABYTE("TB", 1000l * 1000l * 1000l * 1000l),
    PETABYTE("PB", 1000l * 1000l * 1000l * 1000l * 1000l),
    EXABYTE("EB", 1000l * 1000l * 1000l * 1000l * 1000l * 1000l);

    private final String symbol;
    private final long factor;

    DataAmountUnit(final String symbol, final long factor) {
        this.symbol = symbol;
        this.factor = factor;
    }

    public static DataAmountUnit fromString(final String text) {
        for (final DataAmountUnit unit : DataAmountUnit.values()) {
            if (unit.symbol.equals(text)) {
                return unit;
            }
        }

        throw new IllegalArgumentException("Unkown unit " + text + " +");
    }

    public static DataAmountUnit fromStringCaseInsensitive(final String origText) {
        final String text = origText.toLowerCase();
        for (final DataAmountUnit unit : DataAmountUnit.values()) {
            if (unit.symbol.equals(text)) {
                return unit;
            }
        }

        throw new IllegalArgumentException("Unkown unit" + origText + "");
    }

    public String getSymbol() {
        return symbol;
    }

    public long getFactor() {
        return factor;
    }
}
