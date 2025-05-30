/**
 * @file EvictingQueue.java
 * @author Krisna Pranav
 * @version 1.0
 * @date 2025-03-22
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.commons.utils.collect;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;
import java.util.stream.Collectors;
import org.thunderpay.commons.utils.Preconditions;

public class EvictingQueue<E> implements Queue<E> {

    private final int maxSize;
    private final Queue<E> delegate;

    public EvictingQueue(final int maxSize) {
        this.maxSize = maxSize;
        this.delegate = new ArrayDeque<>();
    }

    public int remainingCapacity() {
        return maxSize - size();
    }

    @Override
    public int size() {
        return delegate.size();
    }

    @Override
    public boolean isEmpty() {
        return delegate.isEmpty();
    }

    @Override
    public boolean contains(final Object o) {
        return delegate.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return delegate.iterator();
    }

    @Override
    public Object[] toArray() {
        return delegate.toArray();
    }

    @Override
    public <T> T[] toArray(final T[] array) {
        return delegate.toArray(array);
    }

    @Override
    public boolean add(final E element) {
        Preconditions.checkNotNull(element);
        if (maxSize == 0) {
            return true;
        }
        if (size() == maxSize) {
            delegate.remove();
        }
        delegate.add(element);
        return true;
    }

    @Override
    public boolean remove(final Object o) {
        return delegate.remove(o);
    }

    @Override
    public boolean containsAll(final Collection<?> collection) {
        return delegate.containsAll(collection);
    }

    @Override
    public boolean addAll(final Collection<? extends E> collection) {
        if (collection.isEmpty()) {
            return false;
        }
        final int size = collection.size();
        if (size >= maxSize) {
            clear();
            collection.stream()
                    .skip(size - maxSize)
                    .collect(Collectors.toUnmodifiableList())
                    .forEach(this::add);
        } else {
            collection.forEach(this::add);
        }
        return true;
    }

    @Override
    public boolean removeAll(final Collection<?> c) {
        return delegate.removeAll(c);
    }

    @Override
    public boolean retainAll(final Collection<?> c) {
        return delegate.retainAll(c);
    }

    @Override
    public void clear() {
        delegate.clear();
    }

    @Override
    public boolean offer(final E e) {
        return add(e);
    }

    @Override
    public E remove() {
        return delegate.remove();
    }

    @Override
    public E poll() {
        return delegate.poll();
    }

    @Override
    public E element() {
        return delegate.element();
    }

    @Override
    public E peek() {
        return delegate.peek();
    }
}