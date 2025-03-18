/**
 * @file SubscriptionBundle.java
 * @author Krisna Pranav
 * @version 1.0
 * @date 2025-01-19
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.entitlement.api;


import java.util.List;
import java.util.UUID;
import org.joda.time.DateTime;
import org.thunderpay.billing.util.entity.Entity;

public interface SubscriptionBundle extends Entity {
    public UUID getAccountId();

    public String getExternalKey();

    public DateTime getOriginalCreatedDate();

    public List<Subscription> getSubscriptions();

    public SubscriptionBundleTimeline getTimeline();

}