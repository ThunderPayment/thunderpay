/**
 * @file UriAccessor.java
 * @author Krisna Pranav
 * @brief URI Accessor
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.xmlloader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.JarURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;
import org.thunderpay.commons.utils.io.Resources;
import static java.nio.charset.StandardCharsets.UTF_8;

public class UriAccessor {
    private static final String URI_SCHEME_FOR_ARCHIVE_FILE = "jar:file";
    private static final String URI_SCHEME_FOR_CLASSPATH = "jar";
    private static final String URI_SCHEME_FOR_FILE = "file";

    public static URL toURL(final String uri) throws IOException, URISyntaxException {
        return toURL(new URI(uri));
    }

    public static URL toURL(final URI inputURI) throws IOException, URISyntaxException {
        final String scheme = inputURI.getScheme();
        final URI uri;

        if (scheme == null) {
            
        } else if (scheme.equals(URI_SCHEME_FOR_FILE)) {

        } else {
            uri = inputURI;
        }
        
        return uri.toURL();
    }
}
