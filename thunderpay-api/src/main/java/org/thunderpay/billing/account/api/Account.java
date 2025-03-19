/**
 * @file Account.java
 * @author Krisna Pranav
 * @brief Account
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.account.api;

import org.thunderpay.billing.entitlement.api.Blockable;
import org.thunderpay.billing.util.entity.Entity;

public interface Account extends AccountData, ImmutableAccountData, Blockable, Entity {
    public MutableAccountData toMutableAccountData();
    
    public Account mergeWithDelegate(final Account delegate);
}