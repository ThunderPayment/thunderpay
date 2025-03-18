/**
 * @file RolledUpUsage.java
 * @author Krisna Pranav
 * @brief RolledUpUsage
 * @version 1.0
 * @date 2025-03-18
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.usage.api;

import java.util.List;
import java.util.UUID;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

public interface RolledUpUsage {

    UUID getSubscriptionId();

    DateTime getStart();

    DateTime getEnd();

    List<RolledUpUnit> getRolledUpUnits();
}