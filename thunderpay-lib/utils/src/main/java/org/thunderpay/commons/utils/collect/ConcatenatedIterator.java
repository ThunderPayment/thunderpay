/**
 * @file ConcatenatedIterator.java
 * @author Krisna Pranav
 * @brief Concatenated Iterator
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */


package org.thunderpay.commons.utils.collect;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;
import javax.annotation.CheckForNull;
import org.thunderpay.commons.utils.Preconditions;

class ConcatenatedIterator<T> implements Iterator<T> {
    @CheckForNull
    private Iterator<? extends T> toRemove;

    private Iterator<? extends T> iterator;

    @CheckForNull
    private Iterator<? extends Iterator<? extends T>> topMetaIterator;

    @CheckForNull
    private Deque<Iterator<? extends Iterator<? extends T>>> metaIterators;

    ConcatenatedIterator(final Iterator<? extends Iterator<? extends T>> metaIterator) {
        iterator = Collections.emptyIterator();
        topMetaIterator = Preconditions.checkNotNull(metaIterator);
    }

    @CheckForNull
    private Iterator<? extends Iterator<? extends T>> getTopMetaIterator() {
        while (topMetaIterator == null || !topMetaIterator.hasNext()) {
            if (metaIterators != null && !metaIterators.isEmpty()) {
                topMetaIterator = metaIterators.removeFirst();
            } else {
                return null;
            }
        }

        return topMetaIterator;
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public T next() {
        if (hasNext()) {
            toRemove = iterator;
            return iterator.next();
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public void remove() {
        if (toRemove == null) {
            throw new IllegalStateException("no calls to next since the last call needed to be removed");
        }

        toRemove.remove();
        toRemove = null;
    }
}
