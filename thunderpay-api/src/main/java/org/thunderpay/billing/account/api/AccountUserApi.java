/**
 * @file AccountUserApi.java
 * @author Krisna Pranav
 * @brief Account User Api
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.account.api;

import java.util.List;
import java.util.UUID;
import org.thunderpay.billing.ThunderApi;
import org.thunderpay.billing.security.RequiresPermissions;
import org.thunderpay.billing.util.api.AuditLevel;
import org.thunderpay.billing.util.audit.AuditLogWithHistory;
import org.thunderpay.billing.util.callcontext.CallContext;
import org.thunderpay.billing.util.callcontext.TenantContext;
import org.thunderpay.billing.util.entity.Pagination;
import static org.thunderpay.billing.security.Permission.ACCOUNT_CAN_CREATE;
import static org.thunderpay.billing.security.Permission.ACCOUNT_CAN_UPDATE;
import static org.thunderpay.billing.security.Permission.ACCOUNT_CAN_ADD_EMAILS;
import static org.thunderpay.billing.security.Permission.ACCOUNT_CAN_DELETE_EMAILS;

public interface AccountUserApi extends ThunderApi {

    @RequiresPermissions(ACCOUNT_CAN_CREATE)
    public Account createAccount(AccountData data, CallContext context) throws AccountApiException;

    @RequiresPermissions(ACCOUNT_CAN_UPDATE)
    public void updateAccount(Account account, CallContext context) throws AccountApiException;

    @RequiresPermissions(ACCOUNT_CAN_UPDATE)
    public void updateAccount(String key, AccountData accountData, CallContext context) throws AccountApiException;

    @RequiresPermissions(ACCOUNT_CAN_UPDATE)
    public void updateAccount(UUID accountId, AccountData accountData, CallContext context) throws AccountApiException;

    public Account getAccountByKey(String key, TenantContext context) throws AccountApiException;

    public Account getAccountById(UUID accountId, TenantContext context) throws AccountApiException;

    public Pagination<Account> searchAccounts(String searchKey, Long offset, Long limit, TenantContext context);

    public Pagination<Account> getAccounts(Long offset, Long limit, TenantContext context);

    public UUID getIdFromKey(String externalKey, TenantContext context) throws AccountApiException;

    public List<AccountEmail> getEmails(UUID accountId, TenantContext context);

    @RequiresPermissions(ACCOUNT_CAN_ADD_EMAILS)
    public void addEmail(UUID accountId, AccountEmail email, CallContext context) throws AccountApiException;

    @RequiresPermissions(ACCOUNT_CAN_DELETE_EMAILS)
    public void removeEmail(UUID accountId, AccountEmail email, CallContext context);

    public List<Account> getChildrenAccounts(UUID parentAccountId, TenantContext context) throws AccountApiException;

    List<AuditLogWithHistory> getAuditLogsWithHistoryForId(UUID accountId, AuditLevel auditLevel, TenantContext context) throws AccountApiException;

    List<AuditLogWithHistory> getEmailAuditLogsWithHistoryForId(UUID accountEmailId, AuditLevel auditLevel, TenantContext context) throws AccountApiException;

}