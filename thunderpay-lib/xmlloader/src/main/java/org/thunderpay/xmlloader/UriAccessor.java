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
            uri = new URI(Resources.getResource(inputURI.toString()).toExternalForm());
        } else if (scheme.equals(URI_SCHEME_FOR_FILE) &&
                   !inputURI.getSchemeSpecificPart().startsWith("/")) { // interpret URIs of this form as relative path uris
            uri = new File(inputURI.getSchemeSpecificPart()).toURI();
        } else {
            uri = inputURI;
        }

        return uri.toURL();
    }

    public static InputStream accessUri(final String uri) throws IOException, URISyntaxException {
        return accessUri(new URI(uri));
    }

    public static InputStream accessUri(final URI uri) throws IOException, URISyntaxException {
        final String scheme = uri.getScheme();
        if (URI_SCHEME_FOR_CLASSPATH.equals(scheme)) {
            if (uri.toString().startsWith(URI_SCHEME_FOR_ARCHIVE_FILE)) {
                return getInputStreamFromJarFile(uri);
            } else {
                return UriAccessor.class.getResourceAsStream(uri.getPath());
            }
        }

        final URL url = toURL(uri);
        return url.openConnection().getInputStream();
    }

    private static InputStream getInputStreamFromJarFile(final URI uri) throws IOException {
        final URL url = uri.toURL();
        final JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();

        return jarURLConnection.getJarFile().getInputStream(jarURLConnection.getJarEntry());
    }

    public static String accessUriAsString(final String uri) throws IOException, URISyntaxException {
        return accessUriAsString(new URI(uri));
    }

    public static String accessUriAsString(final URI uri) throws IOException, URISyntaxException {
        final InputStream stream = accessUri(uri);
        return new Scanner(stream, UTF_8.name()).useDelimiter("\\A").next();
    }
}
