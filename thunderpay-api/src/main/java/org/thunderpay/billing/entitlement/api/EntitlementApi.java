/**
 * @file EntitlementApi
 * @author Krisna Pranav
 * @version 1.0
 * @date 2025-01-19
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.entitlement.api;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.joda.time.LocalDate;
import org.thunderpay.billing.ThunderApi;
import org.thunderpay.billing.catalog.api.BillingActionPolicy;
import org.thunderpay.billing.payment.api.PluginProperty;
import org.thunderpay.billing.security.RequiresPermissions;
import org.thunderpay.billing.util.callcontext.CallContext;
import org.thunderpay.billing.util.callcontext.TenantContext;

import static org.thunderpay.billing.security.Permission.ENTITLEMENT_CAN_CREATE;
import static org.thunderpay.billing.security.Permission.ENTITLEMENT_CAN_PAUSE_RESUME;
import static org.thunderpay.billing.security.Permission.ENTITLEMENT_CAN_TRANSFER;

public interface EntitlementApi extends ThunderApi {
    @RequiresPermissions(ENTITLEMENT_CAN_CREATE)
    public UUID createBaseEntitlement(UUID accountId, EntitlementSpecifier spec, String bundleExternalKey, LocalDate entitlementEffectiveDate, LocalDate billingEffectiveDate, boolean isMigrated, boolean renameCancelledBundleIfExist, Iterable<PluginProperty> properties, CallContext context)
            throws EntitlementApiException;

    @RequiresPermissions(ENTITLEMENT_CAN_CREATE)
    List<UUID> createBaseEntitlementsWithAddOns(UUID accountId, Iterable<BaseEntitlementWithAddOnsSpecifier> baseEntitlementWithAddOnsSpecifier, boolean renameCancelledBundleIfExist, Iterable<PluginProperty> properties, CallContext context)
            throws EntitlementApiException;

    @RequiresPermissions(ENTITLEMENT_CAN_CREATE)
    public UUID addEntitlement(UUID bundleId, EntitlementSpecifier spec, LocalDate entitlementEffectiveDate, LocalDate billingEffectiveDate, boolean isMigrated, Iterable<PluginProperty> properties, CallContext context)
            throws EntitlementApiException;

    public List<EntitlementAOStatusDryRun> getDryRunStatusForChange(UUID bundleId, final String targetProductName, final LocalDate effectiveDate, final TenantContext context)
            throws EntitlementApiException;

    @RequiresPermissions(ENTITLEMENT_CAN_PAUSE_RESUME)
    public void pause(UUID bundleId, LocalDate effectiveDate, Iterable<PluginProperty> properties, CallContext context)
            throws EntitlementApiException;

    @RequiresPermissions(ENTITLEMENT_CAN_PAUSE_RESUME)
    public void resume(UUID bundleId, LocalDate effectiveDate, Iterable<PluginProperty> properties, CallContext context)
            throws EntitlementApiException;

    public Entitlement getEntitlementForId(UUID id, boolean includeDeletedEvents, TenantContext context) throws EntitlementApiException;

    public List<Entitlement> getAllEntitlementsForBundle(UUID bundleId, TenantContext context)
            throws EntitlementApiException;

    public List<Entitlement> getAllEntitlementsForAccountIdAndBundleExternalKey(UUID accountId, String bundleExternalKey, TenantContext context)
            throws EntitlementApiException;

    public List<Entitlement> getAllEntitlementsForAccountId(UUID accountId, TenantContext context) throws EntitlementApiException;

    @RequiresPermissions(ENTITLEMENT_CAN_TRANSFER)
    public UUID transferEntitlements(final UUID sourceAccountId, final UUID destAccountId, final String bundleExternalKey, final LocalDate effectiveDate,
                                     final Map<UUID, String> subExtKeys, final BcdTransfer bcdTransfer, final Iterable<PluginProperty> properties, final CallContext context)
            throws EntitlementApiException;

    @RequiresPermissions(ENTITLEMENT_CAN_TRANSFER)
    public UUID transferEntitlementsOverrideBillingPolicy(final UUID sourceAccountId, final UUID destAccountId, final String bundleExternalKey, final LocalDate effectiveDate,
                                                          final Map<UUID, String> subExtKeys, final BillingActionPolicy billingPolicy, final BcdTransfer bcdTransfer,  Iterable<PluginProperty> properties, final CallContext context)
            throws EntitlementApiException;

}