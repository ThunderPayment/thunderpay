/**
 * @file CharStreams.java
 * @author Krisna Pranav
 * @version 1.0
 * @date 2025-03-22
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.commons.utils.io;

import java.io.IOException;
import java.io.Reader;
import org.thunderpay.commons.utils.Preconditions;

public final class CharStreams {

    private static final int DEFAULT_BUF_SIZE = 0x800;

    public static String toString(final Readable r) throws IOException {
        return toStringBuilder(r).toString();
    }

    private static StringBuilder toStringBuilder(final Readable r) throws IOException {
        final StringBuilder sb = new StringBuilder();
        if (r instanceof Reader) {
            copyReaderToBuilder((Reader) r, sb);
        } else {
            throw new RuntimeException("IOUtils#toStringBuilder() parameter should be instance of java.io.Reader");
        }
        return sb;
    }

    static long copyReaderToBuilder(final Reader from, final StringBuilder to) throws IOException {
        Preconditions.checkNotNull(from);
        Preconditions.checkNotNull(to);
        char[] buf = new char[DEFAULT_BUF_SIZE];
        int nRead;
        long total = 0;
        while ((nRead = from.read(buf)) != -1) {
            to.append(buf, 0, nRead);
            total += nRead;
        }
        return total;
    }
}