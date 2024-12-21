/**
 * @file DefaultCoercibles.java
 * @author Krisna Pranav
 * @brief Default Coercibles
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.config;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

final class DefaultCoercibles {
    public static final Coercible<?> CASE_INSENSITIVE_ENUM_COERCIBLE = new CaseInsensitiveEnumCoercible();

    static final Coercible<?> VALUE_OF_COERCIBLE = new Coercible<Object>() {
        private final Map<Class<?>, Coercer<Object>> coercerMap = new HashMap<Class<?>, Coercer<Object>>();

        public Coercer<Object> accept(final Class<?> type) {
            if (coercerMap.containsKey(type)) {
                return coercerMap.get(type);
            }

            Coercer<Object> coercer = null;

            try {
                Method candidate = type.getMethod("valueOf", String.class);

                if (!Modifier.isStatic(candidate.getModifiers())) {
                    candidate = null;
                } else if (!candidate.getReturnType().isAssignableFrom(type)) {
                    candidate = null;
                }

                if (candidate != null) {
                    final Method valueOfMethod = candidate;

                    coercer = new Coercer<Object>() {
                        public Object coerce(final String value) {
                            try {
                                return value == null ? null : valueOfMethod.invoke(null, value);
                            } catch (final Exception e) {
                                throw convertException(e);
                            }
                        }
                    };
                }
            } catch(final NoSuchMethodException nsme) {
            }

            coercerMap.put(type, coercer);
            return coercer;
        }
    };

    static final Coercible<?> STRING_CTOR_COERCIBLE = new Coercible<Object>() {
        private final Map<Class<?>, Coercer<Object>> coercerMap = new HashMap<Class<?>, Coercer<Object>>();

        public Coercer<Object> accept(Class<?> type) {
            if (coercerMap.containsKey(type)) {
                return coercerMap.get(type);
            }

            Coercer<Object> coercer = null;

            try {
                final Constructor<?> ctor = type.getConstructor(String.class);

                coercer = new Coercer<Object>() {
                    public Object coerce(final String value) {
                        try {
                            return value == null ? null : ctor.newInstance(value);
                        } catch (final Exception e) {
                            throw convertException(e);
                        }
                    }
                };
            } catch (final NoSuchMethodException nsme) { }

            coercerMap.put(type, coercer);
            return coercer;
        }
    };

    private DefaultCoercibles() {}

    public static final RuntimeException convertException(final Throwable t) {
        if (t instanceof RuntimeException) {
            return (RuntimeException) t;
        } else if (t instanceof InvocationTargetException) {
            return convertException(((InvocationTargetException) t).getTargetException());
        } else {
            return new RuntimeException(t);
        }
    }
}
