/**
 * @file DefaultCache.java
 * @author Krisna Pranav
 * @brief Default Cache Func
 * @version 1.0
 * @date 2025-03-22
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.commons.utils.cache;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;

import org.thunderpay.commons.utils.Preconditions;
import org.thunderpay.commons.utils.annotation.VisibleForTesting;

public class DefaultCache<K, V> implements Cache<K, V> {

    public static final long NO_TIMEOUT = 0;

    @VisibleForTesting
    final Map<K, TimedValue<V>> map;

    private final long timeoutMillis;
    private final Function<K, V> cacheLoader;

    public DefaultCache(final int maxSize) {
        this(maxSize, NO_TIMEOUT, noCacheLoader());
    }

    public DefaultCache(final Function<K, V> cacheLoader) {
        this(Integer.MAX_VALUE, NO_TIMEOUT, cacheLoader);
    }

    public DefaultCache(final int maxSize, final long timeoutInSecond, final Function<K, V> cacheLoader) {
        Preconditions.checkArgument(maxSize > 0, "cache maxSize should > 0");
        Preconditions.checkArgument(timeoutInSecond >= 0, "cache timeoutInSecond should >= 0");

        this.timeoutMillis = timeoutInSecond * 1_000;
        this.cacheLoader = Preconditions.checkNotNull(cacheLoader, "cacheLoader is null. Use DefaultCache#noCacheLoader() to create a cache without loader");

        map = new LinkedHashMap<>(16, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(final Entry<K, TimedValue<V>> eldest) {
                return size() > maxSize;
            }
        };
    }

    public static <K1, V1> Function<K1, V1> noCacheLoader() {
        return k1 -> null;
    }

    protected boolean isTimeoutEnabled() {
        return timeoutMillis > 0L;
    }

    protected boolean isCacheLoaderExist() {
        return !noCacheLoader().equals(cacheLoader);
    }

    protected void evictExpireEntry(final K key) {
        if (isTimeoutEnabled()) {
            final TimedValue<V> value = map.get(key);
            if (value != null && value.isTimeout()) {
                invalidate(key);
            }
        }
    }

    @Override
    public V get(final K key) {
        Preconditions.checkNotNull(key, "Cannot #get() cache with key = null");

        evictExpireEntry(key);

        final TimedValue<V> timedValue = map.get(key);
        if (timedValue != null) {
            return timedValue.getValue();
        } else if (isCacheLoaderExist()) {
            final V value = cacheLoader.apply(key);
            if (value != null) {
                put(key, value);
            }
            return value;
        } else {
            return null;
        }
    }

    @Override
    public V getOrLoad(final K key, final Function<K, V> loader) {
        Preconditions.checkNotNull(loader, "loader parameter in #getOrLoad() is null");
        final V value = get(key);
        return value == null ? loader.apply(key) : value;
    }

    @Override
    public void put(final K key, final V value) {
        Preconditions.checkNotNull(key, "key in #put() is null");
        Preconditions.checkNotNull(value, "value in #put() is null");

        map.put(key, new TimedValue<>(timeoutMillis, value));
    }

    @Override
    public void invalidate(final K key) {
        Preconditions.checkNotNull(key, "Cannot invalidate. Cache with null key is not allowed");
        map.remove(key);
    }
}