/**
 * @file Subscription.java
 * @author Krisna Pranav
 * @version 1.0
 * @date 2025-01-19
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.entitlement.api;

import java.util.List;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

public interface Subscription extends Entitlement {
    public DateTime getBillingStartDate();

    public DateTime getBillingEndDate();

    public LocalDate getChargedThroughDate();

    public List<SubscriptionEvent> getSubscriptionEvents();
}