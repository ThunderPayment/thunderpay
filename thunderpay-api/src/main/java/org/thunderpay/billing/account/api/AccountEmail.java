/**
 * @file AccountEmail.java
 * @author Krisna Pranav
 * @brief Account Email
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.account.api;

import java.util.UUID;
import org.thunderpay.billing.util.entity.Entity;

public interface AccountEmail extends Entity {
    UUID getAccountId();

    String getEmail();
}