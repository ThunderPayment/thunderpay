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

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.sql.Array;
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
            }
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
