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


}
