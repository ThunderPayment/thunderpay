package org.thunderpay.commons.utils.cache;

import java.util.function.Function;

public class DefaultSynchronizedCache<K, V> extends DefaultCache<K, V> implements Cache<K, V> {

    public DefaultSynchronizedCache(final int maxSize) {
        super(maxSize);
    }

    public DefaultSynchronizedCache(final Function<K, V> cacheLoader) {
        super(cacheLoader);
    }

    public DefaultSynchronizedCache(final int maxSize, final long timeoutInSecond, final Function<K, V> cacheLoader) {
        super(maxSize, timeoutInSecond, cacheLoader);
    }

    @Override
    public V get(final K key) {
        synchronized (this) {
            return super.get(key);
        }
    }

    @Override
    public V getOrLoad(final K key, final Function<K, V> loader) {
        synchronized (this) {
            return super.getOrLoad(key, loader);
        }
    }

    @Override
    public void put(final K key, final V value) {
        synchronized (this) {
            super.put(key, value);
        }
    }

    @Override
    public void invalidate(final K key) {
        synchronized (this) {
            super.invalidate(key);
        }
    }
}