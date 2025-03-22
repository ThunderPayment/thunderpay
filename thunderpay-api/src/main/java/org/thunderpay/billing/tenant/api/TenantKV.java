/**
 * @file TenantKV.java
 * @author Krisna Pranav
 * @brief Tenant KV
 * @version 1.0
 * @date 2025-03-22
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.tenant.api;

import org.thunderpay.billing.util.entity.Entity;

public interface TenantKV extends Entity {

    public String getKey();

    public String getValue();

    public enum TenantKey {
        PUSH_NOTIFICATION_CB(true),
        CATALOG(false),
        OVERDUE_CONFIG(true),
        PER_TENANT_CONFIG(true),
        INVOICE_TRANSLATION_(true),
        CATALOG_TRANSLATION_(true),
        INVOICE_TEMPLATE(true),
        INVOICE_MP_TEMPLATE(true),
        PLUGIN_CONFIG_(true),
        PLUGIN_PAYMENT_STATE_MACHINE_(true);

        private final boolean singleValue;

        TenantKey(final boolean singleValue) {
            this.singleValue = singleValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }
    }
}