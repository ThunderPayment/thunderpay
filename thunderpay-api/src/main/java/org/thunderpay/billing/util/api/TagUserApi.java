/**
 * @file TagUserApi.java
 * @author Krisna Pranav
 * @brief Tag User Api
 * @version 1.0
 * @date 2025-03-19
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.util.api;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.thunderpay.billing.ThunderApi;
import org.thunderpay.billing.ObjectType;
import org.thunderpay.billing.security.RequiresPermissions;
import org.thunderpay.billing.util.audit.AuditLogWithHistory;
import org.thunderpay.billing.util.callcontext.CallContext;
import org.thunderpay.billing.util.callcontext.TenantContext;
import org.thunderpay.billing.util.entity.Pagination;
import org.thunderpay.billing.util.tag.Tag;
import org.thunderpay.billing.util.tag.TagDefinition;

import static org.thunderpay.billing.security.Permission.TAG_CAN_ADD;
import static org.thunderpay.billing.security.Permission.TAG_CAN_CREATE_TAG_DEFINITION;
import static org.thunderpay.billing.security.Permission.TAG_CAN_DELETE_TAG_DEFINITION;
import static org.thunderpay.billing.security.Permission.TAG_CAN_DELETE;

public interface TagUserApi extends ThunderApi {
    public List<TagDefinition> getTagDefinitions(TenantContext context);

    @RequiresPermissions(TAG_CAN_CREATE_TAG_DEFINITION)
    public TagDefinition createTagDefinition(String definitionName, String description, Set<ObjectType> applicableObjectTypes, CallContext context) throws TagDefinitionApiException;

    @RequiresPermissions(TAG_CAN_DELETE_TAG_DEFINITION)
    public void deleteTagDefinition(UUID tagDefinitionId, CallContext context) throws TagDefinitionApiException;

    public TagDefinition getTagDefinition(UUID tagDefinitionId, TenantContext context) throws TagDefinitionApiException;

    public TagDefinition getTagDefinitionForName(String tageDefinitionName, TenantContext context) throws TagDefinitionApiException;

    public List<TagDefinition> getTagDefinitions(Collection<UUID> tagDefinitionIds, TenantContext context) throws TagDefinitionApiException;

    @RequiresPermissions(TAG_CAN_ADD)
    public void addTags(UUID objectId, ObjectType objectType, Collection<UUID> tagDefinitionIds, CallContext context) throws TagApiException;

    @RequiresPermissions(TAG_CAN_ADD)
    public void addTag(UUID objectId, ObjectType objectType, UUID tagDefinitionId, CallContext context) throws TagApiException;

    @RequiresPermissions(TAG_CAN_DELETE)
    public void removeTags(UUID objectId, ObjectType objectType, Collection<UUID> tagDefinitions, CallContext context) throws TagApiException;

    @RequiresPermissions(TAG_CAN_DELETE)
    public void removeTag(UUID objectId, ObjectType objectType, UUID tagDefinitionId, CallContext context) throws TagApiException;

    public Pagination<Tag> searchTags(String searchKey, Long offset, Long limit, TenantContext context);

    public Pagination<Tag> getTags(Long offset, Long limit, TenantContext context);

    public List<Tag> getTagsForObject(UUID objectId, ObjectType objectType, boolean includedDeleted, TenantContext context);

    public List<Tag> getTagsForAccountType(UUID accountId, ObjectType objectType, boolean includedDeleted, TenantContext context);

    public List<Tag> getTagsForAccount(UUID accountId, boolean includedDeleted, TenantContext context);

    List<AuditLogWithHistory> getTagAuditLogsWithHistoryForId(UUID tagId, AuditLevel auditLevel, TenantContext context);

    List<AuditLogWithHistory> getTagDefinitionAuditLogsWithHistoryForId(UUID tagDefinitionId, AuditLevel auditLevel, TenantContext context);

}