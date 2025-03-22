/**
 * @file PaymentMethod.java
 * @author Krisna Pranav
 * @brief Payment Method
 * @version 1.0
 * @date 2025-03-19
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.payment.api;

import java.util.UUID;
import org.thunderpay.billing.util.entity.Entity;

public interface PaymentMethod extends Entity {

    public UUID getId();

    public String getExternalKey();

    public UUID getAccountId();

    public Boolean isActive();

    public String getPluginName();

    public PaymentMethodPlugin getPluginDetail();
}