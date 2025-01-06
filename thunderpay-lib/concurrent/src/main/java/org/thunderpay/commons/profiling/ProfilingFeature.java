/**
 * @file ProfilingFeature
 * @author Krisna Pranav
 * @brief Request
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.commons.profiling;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProfilingFeature {
    private static final int JAXRS_MASK = 0x1;
    private static final int API_MASK = 0x2;
    private static final int DAO_MASK = 0x4;
    private static final int DAO_DETAILS_MASK = 0x8;
    private static final int GLOCK_MASK = 0x10;
    private static final int PLUGIN_MASK = 0x20;
    private static final int DAO_CONNECTION_MASK = 0x40;

    public enum ProfilingFeatureType {
        JAXRS(JAXRS_MASK),
        API(API_MASK),
        DAO(DAO_MASK),
        DAO_DETAILS(DAO_MASK, DAO_DETAILS_MASK),
        DAO_CONNECTION(DAO_CONNECTION_MASK),
        GLOCK(GLOCK_MASK),
        PLUGIN(PLUGIN_MASK);

        private final int mask;

        ProfilingFeatureType(final int... masks) {
            int tmp = 0;
            for (final int mask1 : masks) {
                tmp |= mask1;
            }
            this.mask = tmp;
        }
        public int getMask() {
            return mask;
        }
    }

    private final Pattern featurePattern = Pattern.compile("\\s*,?\\s*((?:[A-Z])+(?:_)?+(?:[A-Z])*)");
    private final int profilingBits;

    public ProfilingFeature() {
        int tmp = 0;
        for (final ProfilingFeatureType cur : ProfilingFeatureType.values()) {
            tmp |= cur.getMask();
        }

        this.profilingBits = tmp;
    }

    public ProfilingFeature(final String features) {
        int tmp = 0;
        final Matcher matcher = featurePattern.matcher(features);

        while(matcher.find()) {
            final String cur = matcher.group(1);
            try {
                final ProfilingFeatureType featureType = ProfilingFeatureType.valueOf(cur);
                tmp |= featureType.getMask();
            } catch (final IllegalArgumentException e) {

            }
        }

        this.profilingBits = tmp;
    }

    public boolean isDefined(final ProfilingFeatureType type) {
        return (profilingBits & type.getMask()) == type.getMask();
    }

    public boolean isProfilingJAXRS() {
        return isDefined(ProfilingFeatureType.JAXRS);
    }
}
