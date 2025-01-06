/**
 * @file Profiling.java
 * @author Krisna Pranav
 * @brief Profiling
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.commons.profiling;

public class Profiling<ReturnType, ExceptionType extends Throwable> {

    private static final ThreadLocal<ProfilingData> perThreadProfilingData = new ThreadLocal<ProfilingData>();

    public interface WithProfilingCallback<ReturnType, ExceptionType extends Throwable> {
        public ReturnType execute() throws ExceptionType;
    }

    public ReturnType executeWithProfiling(final ProfilingFeature.ProfilingFeatureType profilingType, final String profilingId, final WithProfilingCallback<ReturnType, ExceptionType> callback) throws ExceptionType {
        final ProfilingData profilingData = Profiling.getPerThreadProfilingData();
        if (profilingData == null) {
            return callback.execute();
        }
        profilingData.addStart(profilingType, profilingId);
        try {
            return callback.execute();
        } finally {
            profilingData.addEnd(profilingType, profilingId);
        }
    }

    public static ProfilingData getPerThreadProfilingData() {
        return perThreadProfilingData.get();
    }

    public static void setPerThreadProfilingData() {
        final ProfilingFeature profilingFeature = new ProfilingFeature();
        perThreadProfilingData.set(new ProfilingData(profilingFeature));
    }

    public static void setPerThreadProfilingData(final String profileFeatures) {
        final ProfilingFeature profilingFeature = new ProfilingFeature(profileFeatures);
        perThreadProfilingData.set(new ProfilingData(profilingFeature));
    }

    public static void resetPerThreadProfilingData() {
        perThreadProfilingData.set(null);
    }
}
