/**
 * @file MetricTag.java
 * @author Krisna Pranav
 * @brief Metric Tag
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.metric.api.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.PARAMETER;


@Target(PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface MetricTag {
    String tag();
    String property() default "";
}