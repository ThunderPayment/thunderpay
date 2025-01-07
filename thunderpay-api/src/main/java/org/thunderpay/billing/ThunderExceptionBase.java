/**
 * @file ThunderExceptionBase.java
 * @author Krisna Pranav
 * @brief Thunder Exception Base
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing;

public class ThunderExceptionBase extends Exception {
    private static final long serialVersionUID = 165720101383L;
    private final Throwable cause;
    private final int code;
    private final String formattedMsg;

    public ThunderExceptionBase(final Throwable cause, final int code, final String msg) {
        this.cause = cause;
        this.code = code;
        this.formattedMsg = msg;
    }

    public ThunderExceptionBase(final ThunderExceptionBase cause) {
        this(cause, cause.getCode(), cause.getMessage());
    }

    public int getCode() {
        return code;
    }
}
