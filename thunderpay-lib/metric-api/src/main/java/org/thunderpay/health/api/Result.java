/**
 * @file Result.java
 * @author Krisna Pranav
 * @brief Result
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 * @copyright Copyright (c) 2010-2013 Coda Hale, Yammer.com, 2014-2021 Dropwizard Team
 *
 */

package org.thunderpay.health.api;

import java.util.Map;

public interface Result {
    boolean isHealthy();

    String getMessage();

    Throwable getError();

    long getTime();

    Map<String, Object> getDetails();
}
