/**
 * @file AbstractIterator
 * @author Krisna Pranav
 * @brief Abstract Iterator
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */


package org.thunderpay.commons.utils.collect;

import java.util.Iterator;
import java.util.NoSuchElementException;
import javax.annotation.CheckForNull;
import org.thunderpay.commons.utils.Preconditions;

public abstract class AbstractIterator<T> implements Iterator<T> {
    private State state = State.NOT_READY;

    protected AbstractIterator() {}

    private enum State
    {
        READY,
        NOT_READY,
        DONE,
        FAILED,
    }

    @CheckForNull
    private T next;

    @CheckForNull
    protected abstract T computeNext();

    @CheckForNull
    protected final T endOfData() {
        state = State.DONE;
        return null;
    }

    @Override
    public final boolean hasNext() {
        Preconditions.checkState(state != State.FAILED, "AbstractIterator failed");
        switch (state) {
            case DONE:
                return false;
            case READY:
                return true;
            default:
        }

        return tryToComputeNext();
    }

    private boolean tryToComputeNext() {
        state = State.FAILED;
        next = computeNext();
        if (state != State.DONE) {
            state = State.READY;
            return true;
        }

        return false;
    }

    @Override
    public final T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        state = State.NOT_READY;
        final T result = uncheckedCastNullableTToT(next);
        next = null;
        return result;
    }

    public final T peek() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        return uncheckedCastNullableTToT(next);
    }

    static<T> T uncheckedCastNullableTToT(@CheckForNull final T t) {
        return t;
    }
}
