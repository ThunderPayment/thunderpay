/**
 * @file TenantData.java
 * @author Krisna Pranav
 * @brief Tenant Data
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.tenant.api;

public interface TenantData {

    public String getExternalKey();

    public String getApiKey();

    public String getApiSecret();
}