/**
 * @file DryRunArguments.java
 * @author Krisna Pranav
 * @brief Dry Run Arguments
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.invoice.api;

import java.util.UUID;
import org.joda.time.LocalDate;
import org.thunderpay.billing.catalog.api.BillingActionPolicy;
import org.thunderpay.billing.entitlement.api.EntitlementSpecifier;
import org.thunderpay.billing.entitlement.api.SubscriptionEventType;

public interface DryRunArguments {

    DryRunType getDryRunType();

    EntitlementSpecifier getEntitlementSpecifier();

    SubscriptionEventType getAction();

    UUID getSubscriptionId();

    LocalDate getEffectiveDate();

    UUID getBundleId();

    BillingActionPolicy getBillingActionPolicy();
}