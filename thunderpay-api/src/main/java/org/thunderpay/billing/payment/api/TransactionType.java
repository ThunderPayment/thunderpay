/**
 * @file TransactionType.java
 * @author Krisna Pranav
 * @brief Transaction Type
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.payment.api;

public enum TransactionType {
    AUTHORIZE,
    CAPTURE,
    CHARGEBACK,
    CREDIT,
    PURCHASE,
    REFUND,
    VOID
}