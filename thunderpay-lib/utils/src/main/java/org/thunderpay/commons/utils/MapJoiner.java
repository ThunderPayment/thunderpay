/**
 * @file MapJoiner.java
 * @author Krisna Pranav
 * @brief Map Joiner
 * @version 1.0
 * @date 2025-03-22
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.commons.utils;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public final class MapJoiner {

    private final String separator;
    private final String keyValueSeparator;

    public MapJoiner(final String separator, final String keyValueSeparator) {
        this.separator = separator;
        this.keyValueSeparator = keyValueSeparator;
    }

    public String join(final Map<?, ?> map) {
        final StringBuilder sb = new StringBuilder();
        final Iterator<? extends Entry<?, ?>> iterator = map.entrySet().iterator();
        if (iterator.hasNext()) {
            final Map.Entry<?, ?> entry = iterator.next();
            if (isEntryKeyExist(entry)) {
                sb.append(entry.getKey());
                sb.append(separator);
                sb.append(entry.getValue());
            }

            while (iterator.hasNext()) {
                final Map.Entry<?, ?> e = iterator.next();
                if (sb.length() != 0) {
                    sb.append(keyValueSeparator);
                }

                if (isEntryKeyExist(e)) {
                    sb.append(e.getKey());
                    sb.append(separator);
                    sb.append(e.getValue());
                }
            }
        }
        return sb.toString();
    }

    private boolean isEntryKeyExist(final Entry<?, ?> entry) {
        final Object o = entry.getKey();
        if (o == null) {
            return false;
        }
        final String s = o.toString();

        return !(s.isEmpty() && s.isBlank());
    }
}