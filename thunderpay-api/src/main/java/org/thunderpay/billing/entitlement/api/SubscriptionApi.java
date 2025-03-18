/**
 * @file SubscriptionApi.java
 * @author Krisna Pranav
 * @version 1.0
 * @date 2025-01-19
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.entitlement.api;

import java.util.List;
import java.util.UUID;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.thunderpay.billing.ThunderApi;
import org.thunderpay.billing.OrderingType;
import org.thunderpay.billing.payment.api.PluginProperty;
import org.thunderpay.billing.security.RequiresPermissions;
import org.thunderpay.billing.util.api.AuditLevel;
import org.thunderpay.billing.util.audit.AuditLogWithHistory;
import org.thunderpay.billing.util.callcontext.CallContext;
import org.thunderpay.billing.util.callcontext.TenantContext;
import org.thunderpay.billing.util.entity.Pagination;

import static org.thunderpay.billing.security.Permission.ENTITLEMENT_CAN_CREATE;
import static org.thunderpay.billing.security.Permission.ENTITLEMENT_CAN_PAUSE_RESUME;


public interface SubscriptionApi extends ThunderApi {

    int PAST_EVENTS = 0x1;
    int PRESENT_EVENTS = 0x2;
    int FUTURE_EVENTS = 0x4;
    int PAST_OR_PRESENT_EVENTS = (PAST_EVENTS | PRESENT_EVENTS);
    int FUTURE_OR_PRESENT_EVENTS = (PRESENT_EVENTS | FUTURE_EVENTS);
    int ALL_EVENTS = (PAST_OR_PRESENT_EVENTS | FUTURE_OR_PRESENT_EVENTS);

    Subscription getSubscriptionForEntitlementId(UUID entitlementId, boolean includeDeletedEvents, TenantContext context) throws SubscriptionApiException;

    Subscription getSubscriptionForExternalKey(String externalKey, boolean includeDeletedEvents, TenantContext context) throws SubscriptionApiException;

    public SubscriptionBundle getSubscriptionBundle(UUID bundleId, TenantContext context) throws SubscriptionApiException;

    @RequiresPermissions(ENTITLEMENT_CAN_CREATE)
    public void updateExternalKey(UUID bundleId, String newExternalKey, CallContext context) throws EntitlementApiException;

    public List<SubscriptionBundle> getSubscriptionBundlesForAccountIdAndExternalKey(UUID accountId, String externalKey, TenantContext context) throws SubscriptionApiException;

    public SubscriptionBundle getActiveSubscriptionBundleForExternalKey(String externalKey, TenantContext context) throws SubscriptionApiException;

    public List<SubscriptionBundle> getSubscriptionBundlesForExternalKey(String externalKey, TenantContext context) throws SubscriptionApiException;

    public List<SubscriptionBundle> getSubscriptionBundlesForAccountId(UUID accountId, TenantContext context) throws SubscriptionApiException;

    public Pagination<SubscriptionBundle> getSubscriptionBundlesForAccountId(UUID accountId, Long offset, Long limit, TenantContext context) throws SubscriptionApiException;

    public Pagination<SubscriptionBundle> getSubscriptionBundles(Long offset, Long limit, TenantContext context);

    public Pagination<SubscriptionBundle> searchSubscriptionBundles(String searchKey, Long offset, Long limit, TenantContext context);

    @RequiresPermissions(ENTITLEMENT_CAN_PAUSE_RESUME)
    public void addBlockingState(BlockingState blockingState, LocalDate effectiveDate, Iterable<PluginProperty> properties, CallContext context)
            throws EntitlementApiException;

    @RequiresPermissions(ENTITLEMENT_CAN_PAUSE_RESUME)
    public void addBlockingState(BlockingState blockingState, DateTime effectiveDate, Iterable<PluginProperty> properties, CallContext context)
            throws EntitlementApiException;

    public Iterable<BlockingState> getBlockingStates(UUID accountId, List<BlockingStateType> typeFilter, List<String> svcsFilter, OrderingType orderingType, int timeFilter, TenantContext context)
            throws EntitlementApiException;


    List<AuditLogWithHistory> getSubscriptionBundleAuditLogsWithHistoryForId(UUID bundleId, AuditLevel auditLevel, TenantContext context);

    List<AuditLogWithHistory> getSubscriptionAuditLogsWithHistoryForId(UUID entitlementId, AuditLevel auditLevel, TenantContext context);

    List<AuditLogWithHistory> getSubscriptionEventAuditLogsWithHistoryForId(UUID EventId, AuditLevel auditLevel, TenantContext context);

    List<AuditLogWithHistory> getBlockingStateAuditLogsWithHistoryForId(UUID blockingId, AuditLevel auditLevel, TenantContext context);

}