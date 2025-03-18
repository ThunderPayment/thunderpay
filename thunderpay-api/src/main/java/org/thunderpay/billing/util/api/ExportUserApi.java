/**
 * @file ExportUserApi
 * @author Krisna Pranav
 * @brief Export User Api
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.util.api;

import java.io.OutputStream;
import java.util.UUID;
import org.thunderpay.billing.ThunderApi;
import org.thunderpay.billing.security.RequiresPermissions;
import org.thunderpay.billing.util.callcontext.CallContext;
import static org.thunderpay.billing.security.Permission.ADMIN_CAN_EXPORT;

public interface ExportUserApi extends ThunderApi {

    @RequiresPermissions(ADMIN_CAN_EXPORT)
    public void exportDataForAccount(UUID accountId, DatabaseExportOutputStream out, CallContext context);

    @RequiresPermissions(ADMIN_CAN_EXPORT)
    public void exportDataAsCSVForAccount(UUID accountId, OutputStream out, CallContext context);
}