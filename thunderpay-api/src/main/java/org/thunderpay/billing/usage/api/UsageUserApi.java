/**
 * @file UsageUserApi.kava
 * @author Krisna Pranav
 * @brief Usage User Api
 * @version 1.0
 * @date 2025-03-18
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.usage.api;

import java.util.List;
import java.util.UUID;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.thunderpay.billing.ThunderApi;
import org.thunderpay.billing.payment.api.PluginProperty;
import org.thunderpay.billing.security.RequiresPermissions;
import org.thunderpay.billing.util.callcontext.CallContext;
import org.thunderpay.billing.util.callcontext.TenantContext;
import static org.thunderpay.billing.security.Permission.USAGE_CAN_RECORD;

public interface UsageUserApi extends ThunderApi {

    @RequiresPermissions(USAGE_CAN_RECORD)
    public void recordRolledUpUsage(SubscriptionUsageRecord usage, CallContext context) throws UsageApiException;

    public RolledUpUsage getUsageForSubscription(UUID subscriptionId, String unitType, DateTime startDate, DateTime endDate, Iterable<PluginProperty> properties, TenantContext context);

    public List<RolledUpUsage> getAllUsageForSubscription(UUID subscriptionId, List<DateTime> transitionDates, Iterable<PluginProperty> properties, TenantContext context);
}
