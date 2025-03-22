/**
 * @file PaymentGatewayApi.java
 * @author Krisna Pranav
 * @brief Payment Gateway Api
 * @version 1.0
 * @date 2025-03-19
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.payment.api;

import java.util.UUID;
import org.thunderpay.billing.ThunderApi;
import org.thunderpay.billing.account.api.Account;
import org.thunderpay.billing.payment.plugin.api.GatewayNotification;
import org.thunderpay.billing.payment.plugin.api.HostedPaymentPageFormDescriptor;
import org.thunderpay.billing.security.RequiresPermissions;
import org.thunderpay.billing.util.callcontext.CallContext;
import static org.thunderpay.billing.security.Permission.PAYMENT_CAN_PROCESS_NOTIFICATION;

public interface PaymentGatewayApi extends ThunderApi {
    public HostedPaymentPageFormDescriptor buildFormDescriptor(Account account, UUID paymentMethodId, Iterable<PluginProperty> customFields, Iterable<PluginProperty> properties, CallContext context)
            throws PaymentApiException;


    public HostedPaymentPageFormDescriptor buildFormDescriptorWithPaymentControl(Account account, UUID paymentMethodId, Iterable<PluginProperty> customFields, Iterable<PluginProperty> properties, PaymentOptions paymentOptions, CallContext context)
            throws PaymentApiException;

    @RequiresPermissions(PAYMENT_CAN_PROCESS_NOTIFICATION)
    public GatewayNotification processNotification(String notification, String pluginName, Iterable<PluginProperty> properties, CallContext context)
            throws PaymentApiException;


    @RequiresPermissions(PAYMENT_CAN_PROCESS_NOTIFICATION)
    public GatewayNotification processNotificationWithPaymentControl(String notification, String pluginName, Iterable<PluginProperty> properties, PaymentOptions paymentOptions, CallContext context)
            throws PaymentApiException;
}