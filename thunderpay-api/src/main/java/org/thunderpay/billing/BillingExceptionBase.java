package org.thunderpay.billing;

public class BillingExceptionBase extends Exception{
    private static final long serialVersionUID = 165720101383L;

    private final Throwable cause;
    private final int code;
    private final String formattedMsg;


    public BillingExceptionBase(final Throwable cause, final int code, final String msg) {
        this.cause = cause;
        this.code = code;
        this.formattedMsg = msg;
    }
}