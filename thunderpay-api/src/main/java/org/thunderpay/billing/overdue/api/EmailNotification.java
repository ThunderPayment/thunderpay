/**
 * @file EmailNotification.java
 * @author Krisna Pranav
 * @brief Email Notification
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.overdue.api;

public interface EmailNotification {
    public String getSubject();

    public String getTemplateName();

    public Boolean isHTML();
}