/**
 * @file ExactMatchEnumCoercible.java
 * @author Krisna Pranav
 * @brief Exact Match Enum Coercible
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.config;

import java.lang.reflect.Method;

public class ExactMatchEnumCoercible implements Coercible<Object> {
    public Coercer<Object> accept(final Class<?> clazz) {
        if (!clazz.isEnum()) {
            return null;
        }

        try {
            final Method m = clazz.getMethod("valueOf", String.class);
            return new Coercer<Object>() {
                public Object coerce(final String value) {
                    if (value == null) {
                        return null;
                    }

                    try {
                        return m.invoke(null, value);
                    } catch (final Exception e) {
                        throw DefaultCoercibles.convertException(e);
                    }
                }
            };
        } catch (final NoSuchMethodException e) {
            throw new IllegalStateException("<EnumType>.valueOf(String) missing!", e);
        }
    }
}