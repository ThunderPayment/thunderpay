/**
 * @file BillingExceptionBase.java
 * @author Krisna Pranav
 * @brief Billing Exception Base
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing;

public class BillingExceptionBase extends Exception {

    private static final long serialVersionUID = 165720101383L;

    private final Throwable cause;
    private final int code;
    private final String formattedMsg;

    public BillingExceptionBase(final Throwable cause, final int code, final String msg) {
        this.cause = cause;
        this.code = code;
        this.formattedMsg = msg;
    }

    public BillingExceptionBase(final BillingExceptionBase cause) {
        this(cause, cause.getCode(), cause.getMessage());
    }

    public BillingExceptionBase(final Throwable cause, final ErrorCode code, final Object... args) {
        this(cause, code.getCode(), String.format(code.getFormat(), args));
    }

    public BillingExceptionBase(final ErrorCode code, final Object... args) {
        this(null, code, args);
    }

    @Override
    public String getMessage() {
        return formattedMsg;
    }

    @Override
    public Throwable getCause() {
        return cause;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("{cause=").append(cause);
        sb.append(", code=").append(code);
        sb.append(", formattedMsg='").append(formattedMsg).append('\'');
        sb.append('}');
        return sb.toString();
    }
}