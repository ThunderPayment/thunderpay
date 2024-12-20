/**
 * @file ExceptionMetered
 * @author Krisna Pranav
 * @brief Exception Metered
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.metric.api.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
public @interface ExceptionMetered {
    String DEFAULT_NAME_SUFFIX = "exceptions";

    String name() default "";

    boolean absolute() default false;

    Class<? extends Throwable> cause() default Exception.class;
}
