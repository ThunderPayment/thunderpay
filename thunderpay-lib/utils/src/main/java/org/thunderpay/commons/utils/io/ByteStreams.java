/**
 * @file ByteStreams.java
 * @author Krisna Pranav
 * @brief Byte Streams
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.commons.utils.io;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.Queue;
import org.thunderpay.commons.utils.Preconditions;
import org.thunderpay.commons.utils.math.IntMath;

public final class ByteStreams {
    private static final int BUFFER_SIZE = 8192;
    private static final int MAX_ARRAY_LEN = Integer.MAX_VALUE - 8;
    private static final int TO_BYTE_ARRAY_DEQUE_SIZE = 20;

    public static byte[] toByteArray(final InputStream in) throws IOException {
        Preconditions.checkNotNull(in);
        return toByteArrayInternal(in, new ArrayDeque<>(TO_BYTE_ARRAY_DEQUE_SIZE), 0);
    }

    private static byte[] toByteArrayInternal(final InputStream in, final Queue<byte[]> bufs, int totalLen) throws IOException {
        for(int bufSize = BUFFER_SIZE; totalLen < MAX_ARRAY_LEN; bufSize = IntMath.saturatedMultiply(bufSize, 2)) {
            final byte[] buf = new byte[Math.min(bufSize, MAX_ARRAY_LEN - totalLen)];

            bufs.add(buf);
            int off = 0;

            while(off < buf.length) {
                final int r = in.read(buf, off, buf.length - off);
                if(r == -1) {
                    return combineBuffers(bufs, totalLen);
                }

                off += r;
                totalLen += r;
            }
        }

        if (in.read() == -1) {
            return combineBuffers(bufs, MAX_ARRAY_LEN);
        } else {
            throw new OutOfMemoryError("input is too large to fit inside an array");
        }
    }

    private static byte[] combineBuffers(final Queue<byte[]> bufs, final int totalLen) {
        final byte[] result = new byte[totalLen];
        int remaining = totalLen;

        while(remaining > 0) {
            final byte[] buf = bufs.remove();
            final int bytesToCopy = Math.min(remaining, buf.length);
            final int resultOffset = totalLen - remaining;

            System.arraycopy(buf, 0, result, resultOffset, bytesToCopy);
            remaining -= bytesToCopy;
        }

        return result;
    }

}
