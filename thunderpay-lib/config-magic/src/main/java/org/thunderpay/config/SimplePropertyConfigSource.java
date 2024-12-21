/**
 * @file SimplePropertyConfigSource.java
 * @author Krisna Pranav
 * @brief Simple Property Config Source
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.config;

import java.util.Properties;

public class SimplePropertyConfigSource implements ConfigSource {
    private final Properties props;

    public SimplePropertyConfigSource(final Properties props) {
        this.props = new Properties(props);
    }

    public String getString(final String propertyName) {
        return props.getProperty(propertyName);
    }
}
