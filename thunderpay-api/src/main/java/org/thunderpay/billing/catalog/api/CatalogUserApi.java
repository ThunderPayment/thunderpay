/**
 * @file CatalogUserApi.java
 * @author Krisna Pranav
 * @brief Catalog User Api
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.catalog.api;

import org.joda.time.DateTime;
import org.thunderpay.billing.ThunderApi;
import org.thunderpay.billing.security.RequiresPermissions;
import org.thunderpay.billing.util.callcontext.CallContext;
import org.thunderpay.billing.util.callcontext.TenantContext;
import static org.thunderpay.billing.security.Permission.CATALOG_CAN_DELETE;
import static org.thunderpay.billing.security.Permission.CATALOG_CAN_UPLOAD;

public interface CatalogUserApi extends ThunderApi {

    VersionedCatalog getCatalog(String catalogName, TenantContext context) throws CatalogApiException;

    StaticCatalog getCurrentCatalog(String catalogName, TenantContext context) throws CatalogApiException;

    @RequiresPermissions(CATALOG_CAN_UPLOAD)
    void uploadCatalog(String catalogXML, CallContext context) throws CatalogApiException;

    CatalogValidation validateCatalog(String catalogXML, CallContext context);

    @RequiresPermissions(CATALOG_CAN_UPLOAD)
    void createDefaultEmptyCatalog(final DateTime effectiveDate, final CallContext callContext) throws CatalogApiException;

    @RequiresPermissions(CATALOG_CAN_UPLOAD)
    void addSimplePlan(SimplePlanDescriptor planDescriptor, DateTime requestedDate, CallContext context) throws CatalogApiException;

    @RequiresPermissions(CATALOG_CAN_DELETE)
    void deleteCatalog(CallContext callContext) throws CatalogApiException;
}