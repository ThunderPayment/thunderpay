/**
 * @file MultiValueMap.java
 * @author Krisna Pranav
 * @brief MultiValue Map
 * @version 1.0
 * @date 2024-11-25
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