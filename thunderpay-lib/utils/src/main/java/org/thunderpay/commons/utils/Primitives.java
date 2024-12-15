/**
 * @file Primitives
 * @author Krisna Pranav
 * @brief Primitives
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.commons.utils;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public final class Primitives {
    private static final Map<Class<?>, Class<?>> PRIMITIVE_TO_WRAPPER_TYPE;

    private static void add(final Map<Class<?>, Class<?>> forward, final Map<Class<?>, Class<?>> backward, final Class<?> key, final Class<?> value) {
        forward.put(key, value);
        backward.put(value, key);
    }

    public static<T> Class<T> wrap(final Class<T> type) {
        Preconditions.checkNotNull(type);
        @SuppressWarnings("unchecked") final Class<T> wrapped = (Class<T>) PRIMITIVE_TO_WRAPPER_TYPE.get(type);
        return (wrapped == null) ? type : wrapped;
    }
}
