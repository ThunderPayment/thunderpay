/**
 * @file TimedValue.java
 * @author Krisna Pranav
 * @brief Timed Value
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.commons.utils.cache;

import org.thunderpay.commons.utils.Preconditions;
import java.util.Objects;

class TimedValue<V> {
    private final long expireTime;
    private final V value;

    TimedValue(final long timeoutMillis, final V value) {
        this.expireTime = System.currentTimeMillis() + timeoutMillis;
        this.value = Preconditions.checkNotNull(value, "LOG: Timedvalue cannot be null");
    }

    boolean isTimeout() {
        return System.currentTimeMillis() >= expireTime;
    }

    V getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "TimedValue {" + "expireTime=" + expireTime + ", value=" + value + "}";
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final TimedValue<?> that = (TimedValue<?>) o;
        return expireTime == that.expireTime && value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(expireTime, value);
    }
}
