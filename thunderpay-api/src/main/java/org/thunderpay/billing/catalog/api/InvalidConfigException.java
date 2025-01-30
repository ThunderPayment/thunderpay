/**
 * @file InvalidConfigException.java
 * @author Krisna Pranav
 * @brief Invalid Config Exception
 * @version 1.0
 * @date 2025-01-30
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.catalog.api;

public class InvalidConfigException extends Exception {

    private static final long serialVersionUID = 1L;

    public InvalidConfigException(final String arg0, final Throwable arg1) {
        super(arg0, arg1);
    }

    public InvalidConfigException(final String arg0) {
        super(arg0);
    }

}