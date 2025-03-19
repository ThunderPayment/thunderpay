/**
 * @file TransactionStatus.java
 * @author Krisna Pranav
 * @brief Transaction Status
 * @version 1.0
 * @date 2025-03-19
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.payment.api;

public enum TransactionStatus {
    SUCCESS,
    UNKNOWN,
    PENDING,
    PAYMENT_FAILURE,
    PLUGIN_FAILURE,
    PAYMENT_SYSTEM_OFF;
}