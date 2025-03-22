/**
 * @file MultiValueHashMap.java
 * @author Krisna Pranav
 * @version 1.0
 * @date 2025-03-22
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.commons.utils.collect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MultiValueHashMap<K, E> extends HashMap<K, List<E>> implements MultiValueMap<K, E> {

    @Override
    public void putElement(final K key, final E... elements) {
        if (elements == null || elements.length == 0) {
            throw new IllegalArgumentException("MultiValueHashMap#putElement() contains null or empty element");
        }
        if (super.containsKey(key)) {
            super.get(key).addAll(List.of(elements));
        } else {
            super.put(key, new ArrayList<>(List.of(elements)));
        }
    }
}
