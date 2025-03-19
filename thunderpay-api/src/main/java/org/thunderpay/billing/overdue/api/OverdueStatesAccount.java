/**
 * @file OverdueStatusAccount.java
 * @author Krisna Pranav
 * @brief Overdue Status Account
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.overdue.api;

import org.joda.time.Period;

public interface OverdueStatesAccount {
    public Period getInitialReevaluationInterval();
    
    public OverdueState[] getStates();
}