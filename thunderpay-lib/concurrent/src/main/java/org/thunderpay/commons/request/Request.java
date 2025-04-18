/**
 * @file Request.java
 * @author Krisna Pranav
 * @brief Request
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.commons.request;

public class Request {
    private static final ThreadLocal<RequestData> perThreadRequest = new ThreadLocal<RequestData>();

    public static RequestData getPerThreadRequestData() {
        return perThreadRequest.get();
    }

    public static void setPerThreadRequestData(final RequestData requestData) {
        perThreadRequest.set(requestData);
    }

    public static void resetPerThreadRequestData() {
        perThreadRequest.set(null);
    }
}
