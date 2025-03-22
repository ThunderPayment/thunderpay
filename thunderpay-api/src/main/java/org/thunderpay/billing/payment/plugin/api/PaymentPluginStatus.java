/**
 * @file PaymentPluginStatus.java
 * @author Krisna Pranav
 * @brief Payment Plugin Status
 * @version 1.0
 * @date 2025-03-22
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.payment.plugin.api;

public enum PaymentPluginStatus {
    PROCESSED,
    PENDING,
    ERROR,
    UNDEFINED,
    CANCELED;
}