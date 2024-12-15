/**
 * @file IntMath.java
 * @author Krisna Pranav
 * @brief Int Math
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.commons.utils.math;

public final class IntMath {
    public static int saturatedMultiply(final int a, final int b) {
        return Ints.saturatedCast((long) a * b);
    }
}
