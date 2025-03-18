/**
 * @file CustomFieldUserApi.java
 * @author Krisna Pranav
 * @brief Custom Field API Exception
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.util.api;

import java.util.List;
import java.util.UUID;

import org.thunderpay.billing.ThunderApi;
import org.thunderpay.billing.ObjectType;
import org.thunderpay.billing.security.RequiresPermissions;
import org.thunderpay.billing.util.audit.AuditLogWithHistory;
import org.thunderpay.billing.util.callcontext.CallContext;
import org.thunderpay.billing.util.callcontext.TenantContext;
import org.thunderpay.billing.util.customfield.CustomField;
import org.thunderpay.billing.util.entity.Pagination;
import static org.thunderpay.billing.security.Permission.CUSTOM_FIELDS_CAN_ADD;
import static org.thunderpay.billing.security.Permission.CUSTOM_FIELDS_CAN_DELETE;
import static org.thunderpay.billing.security.Permission.CUSTOM_FIELDS_CAN_UPDATE;

public interface CustomFieldUserApi extends ThunderApi {

    public Pagination<CustomField> searchCustomFields(String searchKey, Long offset, Long limit, TenantContext context);

    public Pagination<CustomField> searchCustomFields(final String fieldName, final String fieldValue, final ObjectType objectType, final Long offset, final Long limit, final TenantContext context);

    public Pagination<CustomField> searchCustomFields(final String fieldName, final ObjectType objectType, final Long offset, final Long limit, final TenantContext context);

    public Pagination<CustomField> getCustomFields(Long offset, Long limit, TenantContext context);

    @RequiresPermissions(CUSTOM_FIELDS_CAN_ADD)
    void addCustomFields(List<CustomField> fields, CallContext context) throws CustomFieldApiException;

    @RequiresPermissions(CUSTOM_FIELDS_CAN_UPDATE)
    void updateCustomFields(List<CustomField> fields, CallContext context) throws CustomFieldApiException;

    @RequiresPermissions(CUSTOM_FIELDS_CAN_DELETE)
    void removeCustomFields(List<CustomField> fields, CallContext context) throws CustomFieldApiException;

    List<CustomField> getCustomFieldsForObject(UUID objectId, ObjectType objectType, TenantContext context);

    List<CustomField> getCustomFieldsForAccountType(UUID accountId, ObjectType objectType, TenantContext context);

    List<CustomField> getCustomFieldsForAccount(UUID accountId, TenantContext context);

    List<AuditLogWithHistory> getCustomFieldAuditLogsWithHistoryForId(UUID customFieldId, AuditLevel auditLevel, TenantContext context);

}