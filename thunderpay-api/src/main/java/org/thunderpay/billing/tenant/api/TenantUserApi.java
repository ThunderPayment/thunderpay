/**
 * @file TenantUserApi.java
 * @author Krisna Pranav
 * @brief Tenant User Api
 * @version 1.0
 * @date 2025-03-22
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.tenant.api;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.thunderpay.billing.ThunderApi;
import org.thunderpay.billing.security.RequiresPermissions;
import org.thunderpay.billing.util.callcontext.CallContext;
import org.thunderpay.billing.util.callcontext.TenantContext;
import static org.thunderpay.billing.security.Permission.TENANT_CAN_CREATE;
import static org.thunderpay.billing.security.Permission.TENANT_KEYS_CAN_ADD;
import static org.thunderpay.billing.security.Permission.TENANT_KEYS_CAN_DELETE;

public interface TenantUserApi extends ThunderApi {

    @RequiresPermissions(TENANT_CAN_CREATE)
    public Tenant createTenant(final TenantData data, final CallContext context) throws TenantApiException;

    public Tenant getTenantByApiKey(final String key) throws TenantApiException;

    public Tenant getTenantById(final UUID tenantId) throws TenantApiException;

    public List<String> getTenantValuesForKey(final String key, final TenantContext context) throws TenantApiException;

    public Map<String, List<String>> searchTenantKeyValues(String searchKey, TenantContext context) throws TenantApiException;

    @RequiresPermissions(TENANT_KEYS_CAN_ADD)
    public void addTenantKeyValue(final String key, final String value, final CallContext context) throws TenantApiException;

    @RequiresPermissions(TENANT_KEYS_CAN_ADD)
    public void updateTenantKeyValue(final String key, final String value, final CallContext context) throws TenantApiException;

    @RequiresPermissions(TENANT_KEYS_CAN_DELETE)
    public void deleteTenantKey(final String key, final CallContext context) throws TenantApiException;
}