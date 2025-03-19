/**
 * @file InvoiceItemType.java
 * @author Krisna Pranav
 * @brief Invoice Item Type
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.invoice.api;

public enum InvoiceItemType {
    EXTERNAL_CHARGE,
    FIXED,
    RECURRING,
    REPAIR_ADJ,
    CBA_ADJ,
    CREDIT_ADJ,
    ITEM_ADJ,
    USAGE,
    TAX,
    PARENT_SUMMARY
}