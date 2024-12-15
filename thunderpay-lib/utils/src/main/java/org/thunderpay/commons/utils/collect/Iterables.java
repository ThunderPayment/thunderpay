/**
 * @file Iterables.java
 * @author Krisna Pranav
 * @brief Iterables
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.commons.utils.collect;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.thunderpay.commons.utils.Preconditions;


public final class Iterables {

    public static<T> T getFirst(final Iterable<? extends T> iterable, final T defaultValue) {
        return Iterators.getNext(iterable.iterator(), defaultValue);
    }

    public static<T> T getLast(final Iterable<T> iterable) {
        if(iterable instanceof List) {
            final List<T> list = (List<T>) iterable;

            if(list.isEmpty()) {
                throw new NoSuchElementException("Cannot Iterables on empty list");
            }

            return list.get(list.size() - 1);
        }

        return Iterators.getLast(iterable.iterator());
    }

    public static<T> T getLast(final Iterable<? extends T> iterable, final T defaultValue) {
        if (iterable instanceof Collection) {
            final Collection<? extends T> c = (Collection<? extends T>) iterable;
            if (c.isEmpty()) {
                return defaultValue;
            } else if (iterable instanceof List) {
                final List<? extends T> list = (List<? extends T>) iterable;
                return list.get(list.size() - 1);
            }
        }

        return Iterators.getLast(iterable.iterator(), defaultValue);
    }

    public static<T> Iterable<T> concat(final Iterable<T>... iterables) {
        Preconditions.checkNotNull(iterables);
        final List<T> result = new ArrayList<>();

        for(final Iterable<T> iterable : iterables) {
            Preconditions.checkNotNull(iterable, "One of iterable in iterables is null");
            iterable.forEach(result::add);
        }

        return result;
    }

    public static<T> Set<T> toUnmodifiableSet(final Iterable<? extends T> iterable) {
        return toStream(iterable).collect(Collectors.toUnmodifiableSet());
    }

    public static<T> List<T> toUnmodifiableList(final Iterable<? extends T> iterable) {
        return toStream(iterable).collect(Collectors.toUnmodifiableList());
    }

    public static<T> List<T> toList(final Iterable<? extends T> iterable) {
        return toStream(iterable).collect(Collectors.toList());
    }

    public static<E> Stream<E> toStream(final Iterable<E> iterable) {
        Preconditions.checkNotNull(iterable);
        return StreamSupport.stream(iterable.spliterator(), false);
    }
}
