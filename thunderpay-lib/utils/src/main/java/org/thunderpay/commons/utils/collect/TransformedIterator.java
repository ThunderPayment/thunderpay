/**
 * @file TransformedIterator.java
 * @author Krisna Pranav
 * @brief Transformed Iterator
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.commons.utils.collect;

import java.util.Iterator;
import org.thunderpay.commons.utils.Preconditions;

abstract class TransformedIterator<F, T> implements Iterator<T> {
    final Iterator<? extends F> backingIterator;

    TransformedIterator(final Iterator<? extends F> backingIterator) {
        this.backingIterator = Preconditions.checkNotNull(backingIterator);
    }

    abstract T transform(F form);

    @Override
    public final boolean hasNext() {
        return backingIterator.hasNext();
    }

    @Override
    public final T next() {
        return transform(backingIterator.next());
    }

    @Override
    public final void remove() {
        backingIterator.remove();
    }
}
