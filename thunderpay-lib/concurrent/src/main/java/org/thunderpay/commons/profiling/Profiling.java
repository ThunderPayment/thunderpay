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

public class Profiling<ReturnType, ExeceptionType extends Throwable> {
    private static final ThreadLocal<ProfilingData> perThreadProfilingData = new ThreadLocal<ProfilingData>();

    public interface WithProfilingCallback<ReturnType, ExceptionType extends Throwable> {
        public ReturnType execute() throws ExeceptionType;
    }

    public ReturnType executeWithProfiling(final ProfilingFeature.ProfilingFeatureType profilingType, final String profilingId, final WithProfilingCallback<ReturnType, ExceptionType> callback) throws ExceptionType { {
    }

    public static void resetPerThreadProfilingData() {
        perThreadProfilingData.set(null);
    }
}
