/**
 * @file RequestData.java
 * @author Krisna Pranav
 * @brief Request Data
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.commons.request;

public class RequestData {
    private final String requestId;

    public RequestData(final String requestId) {
        this.requestId = requestId;
    }

    public String getRequestId() {
        return requestId;
    }
}
