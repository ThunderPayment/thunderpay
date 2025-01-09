/**
 * @file TenantContext.java
 * @author Krisna Pranav
 * @brief Tenant Context
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.util.callcontext;

import java.util.UUID;

public interface TenantContext {

    public UUID getAccountId();

    public UUID getTenantId();
}