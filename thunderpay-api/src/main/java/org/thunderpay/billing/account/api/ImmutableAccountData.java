/**
 * @file ImmutableAccountData.java
 * @author Krisna Pranav
 * @brief Immutable Account Data
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.account.api;

import java.util.UUID;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.thunderpay.billing.catalog.api.Currency;

public interface ImmutableAccountData {

    UUID getId();

    String getExternalKey();

    Currency getCurrency();

    DateTimeZone getTimeZone();

    DateTimeZone getFixedOffsetTimeZone();

    DateTime getReferenceTime();
}