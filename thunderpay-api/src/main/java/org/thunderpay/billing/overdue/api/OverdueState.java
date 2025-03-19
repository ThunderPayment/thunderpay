/**
 * @file OverdueState.java
 * @author Krisna Pranav
 * @brief Overdue State
 * @version 1.0
 * @date 2025-03-19
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.overdue.api;

import org.joda.time.Period;
import org.thunderpay.billing.catalog.api.Duration;

public interface OverdueState {
    public OverdueCondition getOverdueCondition();

    public String getName();

    public String getExternalMessage();

    public boolean isBlockChanges();

    public boolean isDisableEntitlementAndChangesBlocked();

    public OverdueCancellationPolicy getOverdueCancellationPolicy();

    public boolean isClearState();

    public Duration getAutoReevaluationInterval() throws OverdueApiException;


}