/**
 * @file InvoiceItem.java
 * @author Krisna Pranav
 * @brief Invoice Item
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.invoice.api;

import java.math.BigDecimal;
import java.util.UUID;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.thunderpay.billing.catalog.api.Currency;
import org.thunderpay.billing.util.entity.Entity;

public interface InvoiceItem extends Entity {

    InvoiceItemType getInvoiceItemType();

    UUID getInvoiceId();

    UUID getAccountId();

    UUID getChildAccountId();

    LocalDate getStartDate();

    LocalDate getEndDate();

    BigDecimal getAmount();

    Currency getCurrency();

    String getDescription();

    UUID getBundleId();

    UUID getSubscriptionId();

    String getProductName();

    String getPrettyProductName();

    String getPlanName();

    String getPrettyPlanName();

    String getPhaseName();

    String getPrettyPhaseName();

    String getUsageName();

    String getPrettyUsageName();

    BigDecimal getRate();

    UUID getLinkedItemId();

    BigDecimal getQuantity();

    String getItemDetails();

    DateTime getCatalogEffectiveDate();

    boolean matches(Object other);
}