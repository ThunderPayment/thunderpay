/**
 * @file ProfilingData
 * @author Krisna Pranav
 * @brief Profiling Data
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.commons.profiling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Nullable;

public class ProfilingData {
    private final List<ProfilingDataItem> rawData;
    private final ProfilingFeature profileFeature;

    public ProfilingData(final ProfilingFeature profileFeature) {
        this.profileFeature = profileFeature;
        this.rawData = new ArrayList<ProfilingDataItem>();
    }

    public void merge(@Nullable final ProfilingData otherData) {
        if (otherData == null || otherData.getRawData().isEmpty()) {
            return;
        }

        rawData.addAll(otherData.getRawData());

        Collections.sort(rawData, new Comparator<ProfilingDataItem>() {
        });
    }

    public static class ProfilingDataItem {
        private final ProfilingFeature.ProfilingFeatureType profileType;
        private final String id;
        private final Long timestampNsec;
        private final LogLineType lineType;

        private ProfilingDataItem(final ProfilingFeature.ProfilingFeatureType profileType, final String id, final LogLineType lineType) {
            this.profileType = profileType;
            this.id = id;
            this.lineType = lineType;
            this.timestampNsec = System.nanoTime();
        }
    }
}
