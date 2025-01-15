/**
 * @file Permission.java
 * @author Krisna Pranav
 * @brief Permission
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.security;

public enum Permission {
    ACCOUNT_CAN_CREATE("account", "create"),
    ACCOUNT_CAN_UPDATE("account", "update"),
    ACCOUNT_CAN_ADD_EMAILS("account", "add_emails"),
    ACCOUNT_CAN_DELETE_EMAILS("account", "delete_emails"),
    ACCOUNT_CAN_CHARGE("account", "charge"),
    ACCOUNT_CAN_CREDIT("account", "credit"),

    CATALOG_CAN_UPLOAD("catalog", "config_upload"),
    CATALOG_CAN_DELETE("catalog", "delete"),

    CUSTOM_FIELDS_CAN_ADD("custom_field", "add"),
    CUSTOM_FIELDS_CAN_UPDATE("custom_field", "update"),
    CUSTOM_FIELDS_CAN_DELETE("custom_field", "delete"),

    ENTITLEMENT_CAN_CREATE("entitlement", "create"),
    ENTITLEMENT_CAN_CHANGE_PLAN("entitlement", "change_plan"),
    ENTITLEMENT_CAN_PAUSE_RESUME("entitlement", "pause_resume"),
    ENTITLEMENT_CAN_CANCEL("entitlement", "cancel"),
    ENTITLEMENT_CAN_TRANSFER("entitlement", "transfer"),

    INVOICE_CAN_CREDIT("invoice", "credit"),
    INVOICE_CAN_ITEM_ADJUST("invoice", "item_adjust"),
    INVOICE_CAN_DELETE_CBA("invoice", "delete_cba"),
    INVOICE_CAN_TRIGGER_INVOICE("invoice", "trigger"),
    INVOICE_CAN_DRY_RUN_INVOICE("invoice", "dry_run"),
    INVOICE_CAN_WRITE_OFF("invoice", "write_off"),
    INVOICE_CAN_COMMIT("invoice", "commit"),
    INVOICE_CAN_VOID("invoice", "void"),

    OVERDUE_CAN_UPLOAD("overdue", "config_upload"),

    PAYMENT_CAN_TRIGGER_PAYMENT("payment", "trigger"),
    PAYMENT_CAN_REFUND("payment", "refund"),
    PAYMENT_CAN_CHARGEBACK("payment", "chargeback"),
    PAYMENT_CAN_TRANSITION("payment", "transition"),
    PAYMENT_CAN_PROCESS_NOTIFICATION("payment", "notification"),

    PAYMENT_METHOD_CAN_CREATE("payment_method", "create"),
    PAYMENT_METHOD_CAN_UPDATE("payment_method", "update"),
    PAYMENT_METHOD_CAN_DELETE("payment_method", "delete"),

    TAG_CAN_CREATE_TAG_DEFINITION("tag", "create_tag_definition"),
    TAG_CAN_DELETE_TAG_DEFINITION("tag", "delete_tag_definition"),
    TAG_CAN_ADD("tag", "add"),
    TAG_CAN_DELETE("tag", "delete"),

    TENANT_CAN_CREATE("tenant", "create"),

    TENANT_KEYS_CAN_ADD("tenant_kvs", "add"),
    TENANT_KEYS_CAN_DELETE("tenant_kvs", "delete"),

    USAGE_CAN_RECORD("usage", "record"),

    USER_CAN_CREATE("user", "create"),

    ADMIN_CAN_FIX_DATA("admin", "update"),
    ADMIN_CAN_EXPORT("admin", "export"),
    ADMIN_CAN_TRIGGER_COMMAND("admin", "trigger_command");


    private final String group;
    private final String value;

    Permission(final String group, final String value) {
        this.group = group;
        this.value = value;
    }

    public String getGroup() {
        return group;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("%s:%s", group, value);
    }
}