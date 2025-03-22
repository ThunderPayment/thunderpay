/**
 * @file Resources.java
 * @author Krisna Pranav
 * @version 1.0
 * @date 2025-03-22
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.commons.utils.io;

import java.net.URL;
import java.util.Objects;
import org.thunderpay.commons.utils.Preconditions;

public final class Resources {
    public static URL getResource(final String resourceName) {
        final ClassLoader loader = Objects.requireNonNullElse(Thread.currentThread().getContextClassLoader(), Resources.class.getClassLoader());
        final URL url = loader.getResource(resourceName);
        Preconditions.checkArgument(url != null, "resource %s not found.", resourceName);
        return url;
    }
}