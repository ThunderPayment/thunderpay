/**
 * @file RecordIdApi.java
 * @author Krisna Pranav
 * @brief Record Id Api
 * @version 1.0
 * @date 2025-03-19
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.util.api;

import java.util.UUID;
import org.thunderpay.billing.ThunderApi;
import org.thunderpay.billing.ObjectType;
import org.thunderpay.billing.util.callcontext.TenantContext;

public interface RecordIdApi extends ThunderApi {
    Long getRecordId(UUID objectId, ObjectType objectType, final TenantContext tenantContext);
}