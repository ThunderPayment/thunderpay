/**
 * @file Joiner.java
 * @author Krisna Pranav
 * @brief Joiner
 * @version 1.0
 * @date 2025-03-22
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

    public static Joiner on(final char separator) {
        return new Joiner(String.valueOf(separator));
    }

    private final String separator;

    private Joiner(final String separator) {
        this.separator = Preconditions.checkNotNull(separator);
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

    public <A extends Appendable> A appendTo(final A appendable, final Iterator<?> parts) throws IOException {
        Preconditions.checkNotNull(appendable);
        if (parts.hasNext()) {
            appendable.append(toString(parts.next()));
            while (parts.hasNext()) {
                appendable.append(separator);
                appendable.append(toString(parts.next()));
            }
        }
        return appendable;
    }

    CharSequence toString(final Object part) {
        requireNonNull(part);
        return (part instanceof CharSequence) ? (CharSequence) part : part.toString();
    }

    public String join(@CheckForNull final Object first, @CheckForNull final Object second, final Object... rest) {
        return join(iterable(first, second, rest));
    }

    private static Iterable<Object> iterable(final Object first, final Object second, final Object[] rest) {
        Preconditions.checkNotNull(rest);
        return new AbstractList<Object>() {
            @Override
            public int size() {
                return rest.length + 2;
            }

            @Override
            @CheckForNull
            public Object get(final int index) {
                switch (index) {
                    case 0:
                        return first;
                    case 1:
                        return second;
                    default:
                        return rest[index - 2];
                }
            }
        };
    }
}