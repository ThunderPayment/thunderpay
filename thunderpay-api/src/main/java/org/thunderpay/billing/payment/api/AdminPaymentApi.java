/**
 * @file AdminPaymentApi.java
 * @author Krisna Pranav
 * @brief Admin Payment Api
 * @version 1.0
 * @date 2025-03-19
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.payment.api;

import org.thunderpay.billing.ThunderApi;
import org.thunderpay.billing.security.RequiresPermissions;
import org.thunderpay.billing.util.callcontext.CallContext;
import static org.thunderpay.billing.security.Permission.ADMIN_CAN_FIX_DATA;

public interface AdminPaymentApi extends ThunderApi {
    @RequiresPermissions(ADMIN_CAN_FIX_DATA)
    public void fixPaymentTransactionState(final Payment payment, PaymentTransaction paymentTransaction, TransactionStatus transactionStatus, String lastSuccessPaymentState, String currentPaymentStateName,
                                           Iterable<PluginProperty> properties, CallContext context)
            throws PaymentApiException;

}