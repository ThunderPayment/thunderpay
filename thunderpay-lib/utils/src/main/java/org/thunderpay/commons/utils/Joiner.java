/**
 * @file Joiner.java
 * @author Krisna Pranav
 * @brief Joiner
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.commons.utils;

import java.io.IOException;
import java.util.AbstractList;
import java.util.Iterator;
import javax.annotation.CheckForNull;
import static java.util.Objects.requireNonNull;

public final class Joiner {
    public static Joiner on(final String separator) {
        return new Joiner(separator);
    }

    public static Joiner on(final char seperator) {
        return new Joiner(String.valueOf(seperator));
    }

    private final String seperator;

    private Joiner(final String seperator) {
        this.seperator = Preconditions.checkNotNull(seperator);
    }

    public String join(final Iterable<?> parts) {
        return join(parts.iterator());
    }

    public String join(final Iterator<?> parts) {
        return appendTo(new StringBuilder(), parts).toString();
    }

    public StringBuilder appendTo(final StringBuilder builder, final Iterator<?> parts) {
        try {
            appendTo((Appendable) builder, parts);
        } catch (final IOException impossible) {
            throw new AssertionError(impossible);
        }
        return builder;
    }
}
