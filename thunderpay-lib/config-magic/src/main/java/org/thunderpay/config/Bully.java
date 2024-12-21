/**
 * @file Bully.java
 * @author Krisna Pranav
 * @brief Bully
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.config;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Bully {
    private static final List<Coercible<?>> TYPE_COERCIBLES;

    private static final List<Coercible<?>> DEFAULT_COERCIBLES;

    static {
        final List<Coercible<?>> typeCoercibles = new ArrayList<Coercible<?>>();
        final List<Coercible<?>> defaultCoercibles = new ArrayList<Coercible<?>>();

        typeCoercibles.add(DefaultCoercibles.BOOLEAN_COERCIBLE);
        typeCoercibles.add(DefaultCoercibles.BYTE_COERCIBLE);
        typeCoercibles.add(DefaultCoercibles.SHORT_COERCIBLE);
        typeCoercibles.add(DefaultCoercibles.INTEGER_COERCIBLE);
        typeCoercibles.add(DefaultCoercibles.LONG_COERCIBLE);
        typeCoercibles.add(DefaultCoercibles.FLOAT_COERCIBLE);
        typeCoercibles.add(DefaultCoercibles.DOUBLE_COERCIBLE);
        typeCoercibles.add(DefaultCoercibles.STRING_COERCIBLE);
        typeCoercibles.add(DefaultCoercibles.URI_COERCIBLE);

        defaultCoercibles.add(DefaultCoercibles.CASE_INSENSITIVE_ENUM_COERCIBLE);
        defaultCoercibles.add(DefaultCoercibles.VALUE_OF_COERCIBLE);
        defaultCoercibles.add(DefaultCoercibles.STRING_CTOR_COERCIBLE);
        defaultCoercibles.add(DefaultCoercibles.OBJECT_CTOR_COERCIBLE);

        TYPE_COERCIBLES = Collections.unmodifiableList(typeCoercibles);
        DEFAULT_COERCIBLES = Collections.unmodifiableList(defaultCoercibles);
    }

    private final Map<Class<?>, Coercer<?>> mappings = new HashMap<Class<?>, Coercer<?>>();


    private final List<Coercible<?>> coercibles = new ArrayList<Coercible<?>>();

    public Bully() {
        coercibles.addAll(TYPE_COERCIBLES);
    }

    public void addCoercible(final Coercible<?> coercible) {
        coercibles.add(coercible);
        mappings.clear();
    }

    public synchronized Object coerce(final Type type, final String value, final Separator separator) {
        if (type instanceof Class) {
            final Class<?> clazz = (Class<?>) type;

            if (clazz.isArray()) {
                return coerceArray(clazz.getComponentType(), value, separator);
            } else if (Class.class.equals(clazz)) {
                return coerceClass(type, null, value);
            } else {
                return coerce(clazz, value);
            }

        } else if (type instanceof ParameterizedType) {
            final ParameterizedType parameterizedType = (ParameterizedType) type;
            final Type rawType = parameterizedType.getRawType();

            if (rawType instanceof Class<?>) {
                final Type[] args = parameterizedType.getActualTypeArguments();

                if (args != null && args.length == 1) {
                    if (args[0] instanceof Class<?>) {
                        return coerceCollection((Class<?>) rawType, (Class<?>) args[0], value, separator);
                    } else if (args[0] instanceof WildcardType) {
                        return coerceClass(type, (WildcardType) args[0], value);
                    }
                }
            }
        }

        throw new IllegalStateException(String.format("%s", type, value));
    }

    private boolean isAssignableFrom(final Type targetType, final Class<?> assignedClass) {
        if (targetType instanceof Class) {
            return ((Class<?>) targetType).isAssignableFrom(assignedClass);
        } else if (targetType instanceof WildcardType) {
            final WildcardType wildcardType = (WildcardType) targetType;
            for (final Type upperBoundType : wildcardType.getUpperBounds()) {
                if (!Object.class.equals(upperBoundType)) {
                    if ((upperBoundType instanceof Class<?>) && !((Class<?>) upperBoundType).isAssignableFrom(assignedClass)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private Class<?> coerceClass(final Type type, final WildcardType wildcardType, final String value) {
        if (value == null) {
            return null;
        } else {
            try {
                final Class<?> clazz = Class.forName(value);

                if (!isAssignableFrom(wildcardType, clazz)) {
                    throw new IllegalArgumentException("Specified class " + clazz + " is not compatible with required type " + type);
                }
                return clazz;
            } catch (final Exception ex) {
                throw new IllegalArgumentException(ex);
            }
        }
    }

    private Object coerceArray(final Class<?> elemType, final String value, final Separator separator) {
        if (value == null) {
            return null;
        } else if (value.length() == 0) {
            return Array.newInstance(elemType, 0);
        } else {
            final String[] tokens = value.split(separator == null ? Separator.DEFAULT : separator.value());
            final Object targetArray = Array.newInstance(elemType, tokens.length);

            for (int idx = 0; idx < tokens.length; idx++) {
                Array.set(targetArray, idx, coerce(elemType, tokens[idx]));
            }

            return targetArray;
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private Object coerceCollection(final Class<?> containerType, final Class<?> elemType, final String value, final Separator separator) {
        if (value == null) {
            return null;
        } else {
            Collection result = null;

            if (Set.class.equals(containerType)) {
                result = new LinkedHashSet();
            } else if (Collection.class.equals(containerType) || List.class.equals(containerType)) {
                result = new ArrayList();
            } else if (Collection.class.isAssignableFrom(containerType)) {
                try {
                    final Constructor<?> ctor = containerType.getConstructor();

                    if (ctor != null) {
                        result = (Collection) ctor.newInstance();
                    }
                } catch (final Exception ex) {}
            }

            if (result == null) {
                throw new IllegalStateException(String.format("%s", containerType, value));
            }

            if (value.length() > 0) {
                for (final String token : value.split(separator == null ? Separator.DEFAULT : separator.value())) {
                    result.add(coerce(elemType, token));
                }
            }

            return result;
        }
    }

    private Object coerce(final Class<?> clazz, final String value) {
        Coercer<?> coercer = getCoercerFor(coercibles, clazz);

        if (coercer == null) {
            coercer = getCoercerFor(DEFAULT_COERCIBLES, clazz);

            if (coercer == null) {
                throw new IllegalStateException(String.format("%s", clazz, value));
            }
        }

        return coercer.coerce(value);
    }

    private Coercer<?> getCoercerFor(final List<Coercible<?>> coercibles, final Class<?> type) {
        Coercer<?> typeCoercer = mappings.get(type);

        if(typeCoercer == null) {
            for (final Coercible<?> coercible : coercibles) {
                final Coercer<?> coercer = coercible.accept(type);

                if (coercer != null) {
                    mappings.put(type, coercer);
                    typeCoercer = coercer;
                    break;
                }
            }
        }

        return typeCoercer;
    }
}
