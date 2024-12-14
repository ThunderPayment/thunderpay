package org.thunderpay.commons.utils.cache;

import java.util.function.Function;

public interface Cache<K, V> {
    V get(K key);

    V getOrLoad(K key, Function<K, V> loader);

    void put(K key, V value);

    void invalidate(K key);
}
