/**
 * @file Sets.java
 * @author Krisna Pranav
 * @version 1.0
 * @date 2025-03-22
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.commons.utils.collect;

import java.util.Set;
import java.util.stream.Collectors;
import org.thunderpay.commons.utils.Preconditions;

public final class Sets {
    public static <E> Set<E> difference(final Set<E> set1, final Set<E> set2) {
        Preconditions.checkNotNull(set1, "set1 in Sets#difference() is null");
        Preconditions.checkNotNull(set2, "set2 in Sets#difference() is null");
        return set1.stream()
                .filter(element -> !set2.contains(element))
                .collect(Collectors.toUnmodifiableSet());
    }
}