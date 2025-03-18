/**
 * @file BaseEntitlementWithAddOnsSpecifier.java
 * @author Krisna Pranav
 * @version 1.0
 * @date 2025-01-19
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.entitlement.api;

import java.util.UUID;
import org.joda.time.DateTime;

public interface BaseEntitlementWithAddOnsSpecifier {

    public UUID getBundleId();

    String getBundleExternalKey();

    Iterable<EntitlementSpecifier> getEntitlementSpecifier();

    DateTime getEntitlementEffectiveDate();

    DateTime getBillingEffectiveDate();

    boolean isMigrated();
}
