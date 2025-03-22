/**
 * @file PaymentOptions.java
 * @author Krisna Pranav
 * @brief Payment Options
 * @version 1.0
 * @date 2025-03-19
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.payment.api;

import java.util.List;

public interface PaymentOptions {
    public boolean isExternalPayment();

    public List<String> getPaymentControlPluginNames();
}