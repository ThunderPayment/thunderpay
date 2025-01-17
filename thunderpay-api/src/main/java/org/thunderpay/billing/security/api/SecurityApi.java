/**
 * @file SecurityApi.java
 * @author Krisna Pranav
 * @brief Security API
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.security.api;

import java.util.List;
import java.util.Set;

import org.thunderpay.billing.ThunderApi;

import org.thunderpay.billing.security.Logical;
import org.thunderpay.billing.security.Permission;
import org.thunderpay.billing.security.RequiresPermissions;
import org.thunderpay.billing.security.SecurityApiException;

import org.thunderpay.billing.util.callcontext.CallContext;
import org.thunderpay.billing.util.callcontext.TenantContext;

import static org.thunderpay.billing.security.Permission.TENANT_CAN_CREATE;
import static org.thunderpay.billing.security.Permission.USER_CAN_CREATE;

public interface SecurityApi extends ThunderApi {
    public void login(Object principal, Object credentials);

    public void logout();

    public boolean isSubjectAuthenticated();

    Set<String> getCurrentUserPermissions(TenantContext context);

    void checkCurrentUserPermissions(List<Permission> permissions, Logical logical, TenantContext context) throws SecurityApiException;

    @RequiresPermissions(USER_CAN_CREATE)
    public void addUserRoles(String username, String clearPassword, List<String> roles, CallContext context) throws SecurityApiException;

    @RequiresPermissions(USER_CAN_CREATE)
    public void updateUserPassword(String username, String clearPassword, CallContext context) throws SecurityApiException;

    @RequiresPermissions(USER_CAN_CREATE)
    public void updateUserRoles(String username, List<String> roles, CallContext context) throws SecurityApiException;

    @RequiresPermissions(USER_CAN_CREATE)
    public void invalidateUser(String username, CallContext context) throws SecurityApiException;

    public List<String> getUserRoles(String username, final TenantContext tenantContext) throws SecurityApiException;

    @RequiresPermissions(USER_CAN_CREATE)
    public void addRoleDefinition(String role, List<String> permissions, CallContext context) throws SecurityApiException;


    @RequiresPermissions(USER_CAN_CREATE)
    public void updateRoleDefinition(String role, List<String> permissions, CallContext context) throws SecurityApiException;

    public List<String> getRoleDefinition(final String role, final TenantContext tenantContext);

}