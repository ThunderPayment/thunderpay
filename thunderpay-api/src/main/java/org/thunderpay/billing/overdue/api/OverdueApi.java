/**
 * @file OverdueApi.java
 * @author Krisna Pranav
 * @brief Overdue Api
 * @version 1.0
 * @date 2025-03-19
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.overdue.api;

import java.util.UUID;

import org.thunderpay.billing.ThunderApi;
import org.thunderpay.billing.account.api.ImmutableAccountData;
import org.thunderpay.billing.security.RequiresPermissions;
import org.thunderpay.billing.util.callcontext.CallContext;
import org.thunderpay.billing.util.callcontext.TenantContext;

import static org.thunderpay.billing.security.Permission.OVERDUE_CAN_UPLOAD;

public interface OverdueApi extends ThunderApi {

    OverdueConfig getOverdueConfig(TenantContext context) throws OverdueApiException;

    @RequiresPermissions(OVERDUE_CAN_UPLOAD)
    void uploadOverdueConfig(String overdueXML, CallContext context) throws OverdueApiException;

    public OverdueState getOverdueStateFor(UUID accountId, TenantContext context) throws OverdueApiException;

    @RequiresPermissions(OVERDUE_CAN_UPLOAD)
    public void uploadOverdueConfig(final OverdueConfig overdueConfig, final CallContext callContext) throws OverdueApiException;
}