package org.thunderpay.commons.utils;

import javax.annotation.CheckForNull;
import javax.annotation.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Preconditions {
    private static final Logger logger = LoggerFactory.getLogger(Preconditions.class);

    public static void checkState(final boolean expression, @CheckForNull final Object errorMessage) {
        if (!expression) {
            throw new IllegalStateException(String.valueOf(errorMessage));
        }
    }

    public static void checkState(final boolean expression, final String errorMessageTemplate, final Object... errorMessageArgs) {
        if (!expression) {
            throw new IllegalStateException(lenientFormat(errorMessageTemplate, errorMessageArgs));
        }
    }

    public static<T> T checkNotNull(@CheckForNull final T reference, final String errorMessageTemplate, @CheckForNull @Nullable final Object... errorMessageArgs) {
        if (reference == null) {
            throw new NullPointerException(lenientFormat(errorMessageTemplate, errorMessageArgs));
        }

        return reference;
    }

    public static void checkState(final boolean b, final String errorMessageTemplate, @CheckForNull final Object p1) {
        if (!b) {
            throw new IllegalStateException(lenientFormat(errorMessageTemplate, p1));
        }
    }

    public static<T> T checkNotNull(final T reference) {
        if (reference == null) {
            throw new NullPointerException();
        }

        return reference;
    }

    private static String lenientFormat(@CheckForNull String template, @CheckForNull Object... args) {
        template = String.valueOf(template);

        if (args == null) {
            args = new Object[] {"(Object[]) null"};
        } else {
            for (int i = 0; i < args.length; i++) {
                args[i] = lenientToString(args[i]);
            }
        }

        final StringBuilder builder = new StringBuilder(template.length() + 16 * args.length);
        int templateStart = 0;
        int i = 0;

        while (i < args.length) {
            final int placeHolderStart = template.indexOf("%s", templateStart);
            if (placeHolderStart == -1) {
                break;
            }

            builder.append(template, templateStart, placeHolderStart);
            builder.append(args[i++]);
            templateStart = placeHolderStart + 2;
        }

        builder.append(template, templateStart, template.length());

        if (i < args.length) {
            builder.append("[ ");
            builder.append(args[i++]);
            while (i < args.length) {
                builder.append(", ");
                builder.append(args[i++]);
            }

            builder.append("]");
        }

        return builder.toString();
    }

    private static String lenientToString(@CheckForNull final Object o) {
        if (o == null) {
            return "null";
        }

        try {
            return o.toString();
        } catch (final Exception e) {
            final String objectToString = o.getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(o));
            logger.warn("Exception during lenientFormat for {}, {}", objectToString, e);
            return "<" + objectToString + " thrw " + e.getClass().getName() + ">";
        }
    }

    @Deprecated
    public static void checkState(final boolean expression) {
        if (!expression) {
            throw new IllegalStateException();
        }
    }

    public static void checkArgument(final boolean b, final String errorMessageTemplate, final Object p1, final int p2) {
        if (!b) {
            throw new IllegalArgumentException(lenientFormat(errorMessageTemplate, p1, p2));
        }
    }

    public static void checkArgument(final boolean b, final String errorMessageTemplate, final Object p1, final Object p2,final Object p3) {
        if (!b) {
            throw new IllegalArgumentException(lenientFormat(errorMessageTemplate, p1, p2, p3));
        }
    }

    public static void checkArgument(final boolean expression, final String errorMessageTemplate, final Object... errorMessageArgs) {
        if (!expression) {
            throw new IllegalArgumentException(lenientFormat(errorMessageTemplate, errorMessageArgs));
        }
    }

}
