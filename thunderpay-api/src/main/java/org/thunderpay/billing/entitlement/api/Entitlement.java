/**
 * @file Entitlement
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
import org.thunderpay.billing.catalog.api.BillingActionPolicy;
import org.thunderpay.billing.catalog.api.Plan;
import org.thunderpay.billing.catalog.api.PlanPhase;
import org.thunderpay.billing.catalog.api.PlanPhasePriceOverride;
import org.thunderpay.billing.catalog.api.PriceList;
import org.thunderpay.billing.catalog.api.Product;
import org.thunderpay.billing.catalog.api.ProductCategory;
import org.thunderpay.billing.payment.api.PluginProperty;
import org.thunderpay.billing.security.RequiresPermissions;
import org.thunderpay.billing.util.callcontext.CallContext;
import org.thunderpay.billing.util.entity.Entity;
import static org.thunderpay.billing.security.Permission.ENTITLEMENT_CAN_CANCEL;
import static org.thunderpay.billing.security.Permission.ENTITLEMENT_CAN_CHANGE_PLAN;
import static org.thunderpay.billing.security.Permission.ENTITLEMENT_CAN_CREATE;


public interface Entitlement extends Entity {
    public enum EntitlementActionPolicy {
        IMMEDIATE,
        END_OF_TERM
    }

    public enum EntitlementState {
        PENDING,
        ACTIVE,
        BLOCKED,
        CANCELLED,
        EXPIRED
    }

    public enum EntitlementSourceType {
        NATIVE,
        MIGRATED,
        TRANSFERRED
    }

    public UUID getBaseEntitlementId();

    public String getExternalKey();

    public UUID getBundleId();

    public String getBundleExternalKey();

    public UUID getAccountId();

    public EntitlementState getState();

    public EntitlementSourceType getSourceType();

    public DateTime getEffectiveStartDate();

    public DateTime getEffectiveEndDate();

    public Product getLastActiveProduct();

    public Plan getLastActivePlan();

    public PlanPhase getLastActivePhase();

    public PriceList getLastActivePriceList();

    public ProductCategory getLastActiveProductCategory();

    public Integer getBillCycleDayLocal();

    public Integer getQuantity();

    @RequiresPermissions(ENTITLEMENT_CAN_CANCEL)
    public Entitlement cancelEntitlementWithDate(final LocalDate effectiveDate, final boolean overrideBillingEffectiveDate, final Iterable<PluginProperty> properties, final CallContext context)
            throws EntitlementApiException;


    @RequiresPermissions(ENTITLEMENT_CAN_CANCEL)
    public Entitlement cancelEntitlementWithDate(final DateTime entitlementEffectiveDate, final DateTime billingEffectiveDate, final Iterable<PluginProperty> properties, final CallContext context)
            throws EntitlementApiException;


    @RequiresPermissions(ENTITLEMENT_CAN_CANCEL)
    public Entitlement cancelEntitlementWithPolicy(final EntitlementActionPolicy policy, final Iterable<PluginProperty> properties, final CallContext context)
            throws EntitlementApiException;

    @RequiresPermissions(ENTITLEMENT_CAN_CANCEL)
    public Entitlement cancelEntitlementWithDateOverrideBillingPolicy(final LocalDate effectiveDate, final BillingActionPolicy billingPolicy, final Iterable<PluginProperty> properties, final CallContext context)
            throws EntitlementApiException;

    @RequiresPermissions(ENTITLEMENT_CAN_CANCEL)
    public Entitlement cancelEntitlementWithPolicyOverrideBillingPolicy(final EntitlementActionPolicy policy, final BillingActionPolicy billingPolicy, final Iterable<PluginProperty> properties, final CallContext context)
            throws EntitlementApiException;

    @RequiresPermissions(ENTITLEMENT_CAN_CANCEL)
    public void uncancelEntitlement(final Iterable<PluginProperty> properties, final CallContext context)
            throws EntitlementApiException;

    @RequiresPermissions(ENTITLEMENT_CAN_CHANGE_PLAN)
    public Entitlement changePlan(final EntitlementSpecifier spec, final Iterable<PluginProperty> properties, final CallContext context)
            throws EntitlementApiException;

    @RequiresPermissions(ENTITLEMENT_CAN_CHANGE_PLAN)
    public void undoChangePlan(final Iterable<PluginProperty> properties, final CallContext context)
            throws EntitlementApiException;

    @RequiresPermissions(ENTITLEMENT_CAN_CHANGE_PLAN)
    public Entitlement changePlanWithDate(final EntitlementSpecifier spec, final LocalDate effectiveDate, final Iterable<PluginProperty> properties, final CallContext context)
            throws EntitlementApiException;

    @RequiresPermissions(ENTITLEMENT_CAN_CHANGE_PLAN)
    public Entitlement changePlanWithDate(final EntitlementSpecifier spec, final DateTime effectiveDate, final Iterable<PluginProperty> properties, final CallContext context)
            throws EntitlementApiException;


    @RequiresPermissions(ENTITLEMENT_CAN_CHANGE_PLAN)
    public Entitlement changePlanOverrideBillingPolicy(final EntitlementSpecifier spec, final LocalDate effectiveDate,
                                                       final BillingActionPolicy billingPolicy, final Iterable<PluginProperty> properties, final CallContext context)
            throws EntitlementApiException;

    @RequiresPermissions(ENTITLEMENT_CAN_CREATE)
    public void updateBCD(final int bcd, final LocalDate effectiveFromDate, final CallContext context) throws EntitlementApiException;

    @RequiresPermissions(ENTITLEMENT_CAN_CREATE)
    public void updateQuantity(final int quantity, final LocalDate effectiveFromDate, final CallContext context) throws EntitlementApiException;

}