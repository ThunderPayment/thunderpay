/**
 * @file InvoiceParentChild.java
 * @author Krisna Pranav
 * @brief Invoice Parent Child
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.invoice.api;

import java.util.UUID;
import org.thunderpay.billing.util.entity.Entity;

public interface InvoiceParentChild extends Entity {

    UUID getParentInvoiceId();

    UUID getChildInvoiceId();

    UUID getChildAccountId();

}