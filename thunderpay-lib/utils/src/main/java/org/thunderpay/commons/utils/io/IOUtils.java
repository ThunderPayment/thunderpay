/**
 * @file IOUtils
 * @author Krisna Pranav
 * @brief IOUtils
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.commons.utils.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.thunderpay.commons.utils.Preconditions;


public final class IOUtils {
    public static String toString(final InputStream inputStream) throws IOException {
        final String result;

        try(inputStream) {
            result = new String(ByteStreams.toByteArray(inputStream), StandardCharsets.UTF_8);
        }

        return result;
    }

    @Deprecated
    public static byte[] toByteArray(final InputStream in) throws IOException {
        Preconditions.checkNotNull(in);
        return ByteStreams.toByteArray(in);
    }

    @Deprecated
    public static URL getResourceAsURL(final String resourceName) {
        return Resources.getResource(resourceName);
    }

    private static final int DEFAULT_BUF_SIZE = 0x800;

    @Deprecated
    public static String toString(final Readable r) throws IOException {
        return toStringBuilder(r).toString();
    }


}
