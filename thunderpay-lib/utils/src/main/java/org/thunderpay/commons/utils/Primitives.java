/**
 * @file Primitives.java
 * @author Krisna Pranav
 * @brief Primitives
 * @version 1.0
 * @date 2025-03-22
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.commons.utils;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public final class Primitives {

    private static final Map<Class<?>, Class<?>> PRIMITIVE_TO_WRAPPER_TYPE;

    static {
        final Map<Class<?>, Class<?>> primToWrap = new LinkedHashMap<>(16);
        final Map<Class<?>, Class<?>> wrapToPrim = new LinkedHashMap<>(16);

        add(primToWrap, wrapToPrim, boolean.class, Boolean.class);
        add(primToWrap, wrapToPrim, byte.class, Byte.class);
        add(primToWrap, wrapToPrim, char.class, Character.class);
        add(primToWrap, wrapToPrim, double.class, Double.class);
        add(primToWrap, wrapToPrim, float.class, Float.class);
        add(primToWrap, wrapToPrim, int.class, Integer.class);
        add(primToWrap, wrapToPrim, long.class, Long.class);
        add(primToWrap, wrapToPrim, short.class, Short.class);
        add(primToWrap, wrapToPrim, void.class, Void.class);

        PRIMITIVE_TO_WRAPPER_TYPE = Collections.unmodifiableMap(primToWrap);
    }

    private static void add(final Map<Class<?>, Class<?>> forward, final Map<Class<?>, Class<?>> backward, final Class<?> key, final Class<?> value) {
        forward.put(key, value);
        backward.put(value, key);
    }


    public static <T> Class<T> wrap(final Class<T> type) {
        Preconditions.checkNotNull(type);

        @SuppressWarnings("unchecked") final Class<T> wrapped = (Class<T>) PRIMITIVE_TO_WRAPPER_TYPE.get(type);
        return (wrapped == null) ? type : wrapped;
    }
}