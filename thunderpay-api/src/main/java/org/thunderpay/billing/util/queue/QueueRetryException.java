/**
 * @file QueueRetryException.java
 * @author Krisna Pranav
 * @brief Queue Retry Exception
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.util.queue;

import java.util.List;
import org.joda.time.Period;

public class QueueRetryException extends RuntimeException {

    public static final List<Period> DEFAULT_RETRY_SCHEDULE = List.of(Period.minutes(5), Period.minutes(15), Period.hours(1), Period.hours(6), Period.hours(24));
    private final List<Period> retrySchedule;

    public QueueRetryException() {
        this(null, null);
    }

    public QueueRetryException(final Exception e) {
        this(e, null);
    }

    public QueueRetryException(final List<Period> retrySchedule) {
        this(null, retrySchedule);
    }

    public QueueRetryException(final Exception e, final List<Period> retrySchedule) {
        super(e);
        this.retrySchedule = retrySchedule != null ? retrySchedule : DEFAULT_RETRY_SCHEDULE;
    }

    public List<Period> getRetrySchedule() {
        return List.copyOf(retrySchedule);
    }

    @Override
    public String toString() {
        return String.format("%s (retrySchedule: %s)", super.toString(), retrySchedule);
    }
}