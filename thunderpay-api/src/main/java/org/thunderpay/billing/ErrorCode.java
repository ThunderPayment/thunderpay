/**
 * @file ErrorCode.java
 * @author Krisna Pranav
 * @brief Error Code
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing;

public enum ErrorCode {

    __UNKNOWN_EROR_CODE(-1, "Unknown error code");
    private final int code;
    private final String format;

    ErrorCode(final int code, final String format) {
        this.code = code;
        this.format = format;
    }

    public String getFormat() {
        return format;
    }

    public int getCode() {
        return code;
    }

    public static ErrorCode fromCode(final int code) {
        for(final ErrorCode errorCode : ErrorCode.values()) {
            if (errorCode.getCode() == code) {
                return errorCode;
            }
        }

        return __UNKNOWN_EROR_CODE;
    }
}
