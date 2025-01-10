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
    public static final List<Period> DEFAULT_RETRY_SCHEDULE = List.of(Period.minutes(5));

    private final List<Period> retrySchedule;

    public QueueRetryException() {
        this(null, null);
    }

    public QueueRetryException(final Exception e) {
        this(e, null);
    }
}
