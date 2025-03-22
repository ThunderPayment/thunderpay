/**
 * @file Iterators.java
 * @author Krisna Pranav
 * @version 1.0
 * @date 2025-03-22
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.commons.utils.collect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import javax.annotation.CheckForNull;
import org.thunderpay.commons.utils.Preconditions;

public final class Iterators {
    public static <T> T getNext(final Iterator<? extends T> iterator, final T defaultValue) {
        return iterator.hasNext() ? iterator.next() : defaultValue;
    }

    public static <T> T getLast(final Iterator<T> iterator) {
        while (true) {
            final T current = iterator.next();
            if (!iterator.hasNext()) {
                return current;
            }
        }
    }

    public static <F, T> Iterator<T> transform(final Iterator<F> fromIterator, final Function<? super F, ? extends T> function) {
        Preconditions.checkNotNull(function);
        return new TransformedIterator<F, T>(fromIterator) {
            @Override
            T transform(final F from) {
                return function.apply(from);
            }
        };
    }

    public static <E> List<E> toUnmodifiableList(final Iterator<? extends E> elements) {
        if (!elements.hasNext()) {
            return Collections.emptyList();
        }

        final E first = elements.next();

        if (!elements.hasNext()) {
            return List.of(first);
        } else {
            final List<E> result = new ArrayList<>();
            result.add(first);
            elements.forEachRemaining(result::add);
            return List.copyOf(result);
        }
    }

    public static int size(final Iterator<?> iterator) {
        int count = 0;
        while (iterator.hasNext()) {
            iterator.next();
            count++;
        }
        return count;
    }

    public static <E> Stream<E> toStream(final Iterator<E> iterator) {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED), false);
    }

    public static boolean contains(final Iterator<?> iterator, @CheckForNull final Object element) {
        if (element == null) {
            while (iterator.hasNext()) {
                if (iterator.next() == null) {
                    return true;
                }
            }
        } else {
            while (iterator.hasNext()) {
                if (element.equals(iterator.next())) {
                    return true;
                }
            }
        }
        return false;
    }

    public static <T> T getLast(final Iterator<? extends T> iterator, final T defaultValue) {
        return iterator.hasNext() ? getLast(iterator) : defaultValue;
    }

    public static <T> Iterator<T> concat(final Iterator<? extends Iterator<? extends T>> inputs) {
        return new ConcatenatedIterator<>(inputs);
    }

    public static <T extends Object> T getOnlyElement(final Iterator<T> iterator) {
        final T first = iterator.next();
        if (!iterator.hasNext()) {
            return first;
        }

        final StringBuilder sb = new StringBuilder().append("expected one element but was: <").append(first);
        for (int i = 0; i < 4 && iterator.hasNext(); i++) {
            sb.append(", ").append(iterator.next());
        }
        if (iterator.hasNext()) {
            sb.append(", ...");
        }
        sb.append('>');

        throw new IllegalArgumentException(sb.toString());
    }
}