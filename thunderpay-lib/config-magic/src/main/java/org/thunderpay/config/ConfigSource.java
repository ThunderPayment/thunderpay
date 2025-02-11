/**
 * @file ConfigSource.java
 * @author Krisna Pranav
 * @brief Config Source
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.config;

public interface ConfigSource {
    String getString(String propertyName);
}