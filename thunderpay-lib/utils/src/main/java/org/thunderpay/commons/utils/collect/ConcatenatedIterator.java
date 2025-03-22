/**
 * @file ConcatenatedIterator.java
 * @author Krisna Pranav
 * @brief Concatenated Iterator
 * @version 1.0
 * @date 2025-03-22
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
        while (!Preconditions.checkNotNull(iterator).hasNext()) {
            topMetaIterator = getTopMetaIterator();
            if (topMetaIterator == null) {
                return false;
            }

            iterator = topMetaIterator.next();

            if (iterator instanceof ConcatenatedIterator) {
                @SuppressWarnings("unchecked")
                final ConcatenatedIterator<T> topConcat = (ConcatenatedIterator<T>) iterator;
                iterator = topConcat.iterator;

                if (this.metaIterators == null) {
                    this.metaIterators = new ArrayDeque<>();
                }

                this.metaIterators.addFirst(this.topMetaIterator);

                if (topConcat.metaIterators != null) {
                    while (!topConcat.metaIterators.isEmpty()) {
                        Preconditions
                                .checkNotNull(this.metaIterators)
                                .addFirst(Preconditions.checkNotNull(topConcat.metaIterators.removeLast()));
                    }
                }
                this.topMetaIterator = topConcat.topMetaIterator;
            }
        }
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
            throw new IllegalStateException("no calls to next() since the last call to remove()");
        }
        toRemove.remove();
        toRemove = null;
    }
}
