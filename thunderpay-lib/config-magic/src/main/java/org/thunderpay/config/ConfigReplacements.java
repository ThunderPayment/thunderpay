/**
 * @file ConfigReplacements.java
 * @author Krisna Pranav
 * @brief Config Replacements
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ConfigReplacements {
    String DEFAULT_VALUE = "__%%%noValue%%%__";

    String value() default DEFAULT_VALUE;
}