/**
 * @file CaseInsensitiveEnumCoercible.java
 * @author Krisna Pranav
 * @brief Case Insensitive Enum Coercible
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.config;

import java.util.Arrays;

public class CaseInsensitiveEnumCoercible implements Coercible<Object> {

    public Coercer<Object> accept(final Class<?> clazz) {
        if (!clazz.isEnum()) {
            return null;
        }

        final Enum<?>[] values;
        try {
            values = (Enum[]) clazz.getMethod("values").invoke(null);
        } catch (final Exception e) {
            throw new IllegalStateException("unable to access <EnumType>.values() static method", e);
        }

        return new Coercer<Object>() {
            public Object coerce(final String value) {
                if (value == null) {
                    return null;
                }
                for (final Object o : values) {
                    if (value.equalsIgnoreCase(o.toString())) {
                        return o;
                    }
                }
                throw new IllegalStateException("No enum value of " + Arrays.toString(values) + " matches [" + value + "]");
            }
        };
    }
}