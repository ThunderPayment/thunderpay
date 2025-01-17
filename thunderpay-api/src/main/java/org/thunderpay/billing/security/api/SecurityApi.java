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

public class SecurityApi extends ThunderApi {
    public void login(Object principal, Object credentials);

    public void logout();

    @RequiresPermissions(USER_CAN_CREATE)
    public void updateRoleDefinition(String role, List<String> permissions, CallContext context) throws SecurityApiException;
}
