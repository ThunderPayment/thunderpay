/**
 * @file MultiValueMap.java
 * @author Krisna Pranav
 * @version 1.0
 * @date 2025-03-22
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.commons.utils.collect;

import java.util.List;
import java.util.Map;

public interface MultiValueMap<K, E> extends Map<K, List<E>> {
    void putElement(K key, E... elements);
}
