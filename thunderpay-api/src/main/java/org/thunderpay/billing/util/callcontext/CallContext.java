/**
 * @file CallContext.java
 * @author Krisna Pranav
 * @brief Call Context
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.util.callcontext;

import java.util.UUID;
import org.joda.time.DateTime;

public interface CallContext extends TenantContext {
    public UUID getUserToken();

    public String getUserName();

    public CallOrigin getCallOrigin();

    public UserType getUserType();

    public String getReasonCode();

    public String getComments();

    public DateTime getCreatedDate();

    public DateTime getUpdatedDate();
}
