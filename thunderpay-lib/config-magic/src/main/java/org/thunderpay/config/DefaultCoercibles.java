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
import org.thunderpay.config.Coercer;
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
            } catch (final NoSuchMethodException nsme) { }

            coercerMap.put(type, coercer);
            return coercer;
        }
    };

    static final Coercible<?> STRING_CTOR_COERCIBLE = new Coercible<Object>() {

        private final Map<Class<?>, Coercer<Object>> coercerMap = new HashMap<Class<?>, Coercer<Object>>();

        public Coercer<Object> accept(final Class<?> type) {
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

    static final Coercible<?> OBJECT_CTOR_COERCIBLE = new Coercible<Object>() {

        private final Map<Class<?>, Coercer<Object>> coercerMap = new HashMap<Class<?>, Coercer<Object>>();

        public Coercer<Object> accept(final Class<?> type) {
            if (coercerMap.containsKey(type)) {
                return coercerMap.get(type);
            }

            Coercer<Object> coercer = null;
            try {
                final Constructor<?> ctor = type.getConstructor(Object.class);

                coercer = new Coercer<Object>() {
                    public Object coerce(final String value) {
                        try {
                            return ctor.newInstance(value);
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

    static final Coercer<Boolean> BOOLEAN_COERCER = new Coercer<Boolean>() {
        @SuppressFBWarnings(value = "NP_BOOLEAN_RETURN_NULL", justification = "Using annotation because cannot excluded via xml")
        public Boolean coerce(final String value) {
            return value != null ? Boolean.valueOf(value) : null;
        }
    };

    static final Coercible<Boolean> BOOLEAN_COERCIBLE = new Coercible<Boolean>() {
        public Coercer<Boolean> accept(final Class<?> clazz) {
            if (Boolean.class.isAssignableFrom(clazz) || Boolean.TYPE.isAssignableFrom(clazz)) {
                return DefaultCoercibles.BOOLEAN_COERCER;
            }
            return null;
        }
    };

    static final Coercer<Byte> BYTE_COERCER = new Coercer<Byte>() {
        public Byte coerce(final String value) {
            return value != null ? Byte.valueOf(value) : null;
        }
    };

    static final Coercible<Byte> BYTE_COERCIBLE = new Coercible<Byte>() {
        public Coercer<Byte> accept(final Class<?> clazz) {
            if (Byte.class.isAssignableFrom(clazz) || Byte.TYPE.isAssignableFrom(clazz)) {
                return DefaultCoercibles.BYTE_COERCER;
            }
            return null;
        }
    };

    static final Coercer<Short> SHORT_COERCER = new Coercer<Short>() {
        public Short coerce(final String value) {
            return value != null ? Short.valueOf(value) : null;
        }
    };

    static final Coercible<Short> SHORT_COERCIBLE = new Coercible<Short>() {
        public Coercer<Short> accept(final Class<?> clazz) {
            if (Short.class.isAssignableFrom(clazz) || Short.TYPE.isAssignableFrom(clazz)) {
                return DefaultCoercibles.SHORT_COERCER;
            }
            return null;
        }
    };

    static final Coercer<Integer> INTEGER_COERCER = new Coercer<Integer>() {
        public Integer coerce(final String value) {
            return value != null ? Integer.valueOf(value) : null;
        }
    };

    static final Coercible<Integer> INTEGER_COERCIBLE = new Coercible<Integer>() {
        public Coercer<Integer> accept(final Class<?> clazz) {
            if (Integer.class.isAssignableFrom(clazz) || Integer.TYPE.isAssignableFrom(clazz)) {
                return DefaultCoercibles.INTEGER_COERCER;
            }
            return null;
        }
    };

    static final Coercer<Long> LONG_COERCER = new Coercer<Long>() {
        public Long coerce(final String value) {
            return value != null ? Long.valueOf(value) : null;
        }
    };

    static final Coercible<Long> LONG_COERCIBLE = new Coercible<Long>() {
        public Coercer<Long> accept(final Class<?> clazz) {
            if (Long.class.isAssignableFrom(clazz) || Long.TYPE.isAssignableFrom(clazz)) {
                return DefaultCoercibles.LONG_COERCER;
            }
            return null;
        }
    };

    static final Coercer<Float> FLOAT_COERCER = new Coercer<Float>() {
        public Float coerce(final String value) {
            return value != null ? Float.valueOf(value) : null;
        }
    };

    static final Coercible<Float> FLOAT_COERCIBLE = new Coercible<Float>() {
        public Coercer<Float> accept(final Class<?> clazz) {
            if (Float.class.isAssignableFrom(clazz) || Float.TYPE.isAssignableFrom(clazz)) {
                return DefaultCoercibles.FLOAT_COERCER;
            }
            return null;
        }
    };

    static final Coercer<Double> DOUBLE_COERCER = new Coercer<Double>() {
        public Double coerce(final String value) {
            return value != null ? Double.valueOf(value) : null;
        }
    };

    static final Coercible<Double> DOUBLE_COERCIBLE = new Coercible<Double>() {
        public Coercer<Double> accept(final Class<?> clazz) {
            if (Double.class.isAssignableFrom(clazz) || Double.TYPE.isAssignableFrom(clazz)) {
                return DefaultCoercibles.DOUBLE_COERCER;
            }
            return null;
        }
    };

    static final Coercer<String> STRING_COERCER = new Coercer<String>() {
        public String coerce(final String value) {
            return value;
        }
    };

    static final Coercible<String> STRING_COERCIBLE = new Coercible<String>() {
        public Coercer<String> accept(final Class<?> clazz) {
            if (String.class.equals(clazz)) {
                return DefaultCoercibles.STRING_COERCER;
            }
            return null;
        }
    };

    static final Coercer<URI> URI_COERCER = new Coercer<URI>() {
        public URI coerce(final String value) {
            return value != null ? URI.create(value) : null;
        }
    };

    static final Coercible<URI> URI_COERCIBLE = new Coercible<URI>() {
        public Coercer<URI> accept(final Class<?> clazz) {
            if (URI.class.equals(clazz)) {
                return DefaultCoercibles.URI_COERCER;
            }
            return null;
        }
    };

    private DefaultCoercibles() { }

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
