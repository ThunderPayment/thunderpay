/**
 * @file TypeToken.java
 * @author Krisna Pranav
 * @brief Type Token
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.commons.utils;

import java.util.LinkedHashSet;
import java.util.Set;

public final class TypeToken {

    public static Set<Class<?>> getRawTypes(final Class<?> clazz) {
        final Set<Class<?>> result = new LinkedHashSet<>();

        result.add(clazz);
        result.addAll(getInterfaces(clazz));

        Class<?> superClass = clazz.getSuperclass();

        while (superClass != null) {
            result.addAll(getRawTypes(superClass));
            superClass = superClass.getSuperclass();
        }

        return result;
    }

    static Set<Class<?>> getInterfaces(final Class<?> clazz) {
        final Set<Class<?>> result = new LinkedHashSet<>();
        Set<Class<?>> interfaces = Set.of(clazz.getInterfaces());

        while (!interfaces.isEmpty()) {
            result.addAll(interfaces);
            for(final Class<?> anInterface : interfaces) {
                interfaces = Set.of(anInterface.getInterfaces());
                result.addAll(interfaces);
            }
        }

        return result;
    }
}
