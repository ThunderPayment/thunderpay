/**
 * @file InvoicePaymentType.java
 * @author Krisna Pranav
 * @brief Invoice Payment Type
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.invoice.api;

public enum InvoicePaymentType {
    ATTEMPT,
    CHARGED_BACK,
    REFUND;
}