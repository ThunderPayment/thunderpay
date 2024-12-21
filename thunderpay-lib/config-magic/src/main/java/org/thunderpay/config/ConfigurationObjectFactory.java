/**
 * @file ConfigurationObjectFactory.java
 * @author Krisna Pranav
 * @brief Configuration Object Factory
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.config;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import edu.umd.cs.findbugs.annotations.Nullable;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType.Builder;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.implementation.InvocationHandlerAdapter;
import net.bytebuddy.implementation.SuperMethodCall;
import net.bytebuddy.matcher.ElementMatchers;

public class ConfigurationObjectFactory {
    private static final Logger logger = LoggerFactory.getLogger(ConfigurationObjectFactory.class);

    private final ConfigSource config;
    private final Bully bully;

    public ConfigurationObjectFactory(final Properties props) {
        this (new SimplePropertyConfigSource(props));
    }

    public ConfigurationObjectFactory(final ConfigSource config) {
        this.config = config;
        this.bully = new Bully();
    }

    public void addCoercible(final Coercible<?> coercible) {
        this.bully.addCoercible(coercible);
    }

    public<T> T buildWithReplacements(final Class<T> configClass, final Map<String, String> mappedReplacements) {
        return internalBuild(configClass, mappedReplacements);
    }

    public<T> T build(final Class<T> configClass) {
        return internalBuild(configClass, null);
    }

    private <T> T internalBuild(final Class<T> configClass, @Nullable final Map<String, String> mappedReplacements) {
        Builder<T> bbBuilder = new ByteBuddy().subclass(configClass);

        for (final Method method : configClass.getMethods()) {
            if (method.isAnnotationPresent(Config.class)) {
                final Config annotation = method.getAnnotation(Config.class);

                if (method.getParameterTypes().length > 0) {
                    if (mappedReplacements != null) {
                        throw new RuntimeException("Replacements are not supported");
                    }
                    bbBuilder = buildParameterized(bbBuilder, method, annotation);
                } else {
                    bbBuilder = buildSimple(bbBuilder, method, annotation, mappedReplacements, null);
                }
            } else if (method.isAnnotationPresent(ConfigReplacements.class)) {
                final ConfigReplacements annotation = method.getAnnotation(ConfigReplacements.class);

                if (ConfigReplacements.DEFAULT_VALUE.equals(annotation.value())) {
                    final Map<String, String> fixedMap = mappedReplacements == null ?
                            Collections.<String, String>emptyMap() : Collections.unmodifiableMap(mappedReplacements);

                    bbBuilder = bbBuilder.method(ElementMatchers.is(method)).intercept(FixedValue.value(fixedMap));
                } else {
                    bbBuilder = buildSimple(bbBuilder, method, null, mappedReplacements, annotation);
                }
            } else if (Modifier.isAbstract(method.getModifiers())) {
                throw new AbstractMethodError(String.format("lacks an @Config annotation",
                        method.toGenericString()));
            }
        }

        final Class<?> loaded = bbBuilder.make()
                .load(configClass.getClassLoader(), ClassLoadingStrategy.Default.INJECTION)
                .getLoaded();

        try {
            return configClass.cast(loaded.getConstructor().newInstance());
        } catch (final ReflectiveOperationException e) {
            throw new AssertionError("Failed to instantiate proxy class for " + configClass.getName(), e);
        }
    }

    private <T> Builder<T> buildSimple(final Builder<T> bbBuilder, final Method method, final Config annotation, final Map<String, String> mappedReplacements, final ConfigReplacements mapAnnotation) {
        String[] propertyNames = new String[0];
        String value = null;


        if (annotation != null) {
            propertyNames = annotation.value();

            if (propertyNames.length == 0) {
                throw new IllegalArgumentException("Method " +
                        method.toGenericString() +
                        " declares config annotation but no field name!");
            }

            for (String propertyName : propertyNames) {
                if (mappedReplacements != null) {
                    propertyName = applyReplacements(propertyName, mappedReplacements);
                }
                value = config.getString(propertyName);

                // First value found wins
                if (value != null) {
                    logger.info("Assigning value [{}] for [{}] on [{}#{}()]",
                            value, propertyName, method.getDeclaringClass().getName(), method.getName());
                    break;
                }
            }
        } else {
            if (mapAnnotation == null) {
                throw new IllegalStateException("!");
            }
            final String key = mapAnnotation.value();
            value = mappedReplacements == null ? null : mappedReplacements.get(key);

            if (value != null) {
                logger.info(" value [{}] for [{}] on [{}#{}()]",
                        value, key, method.getDeclaringClass().getName(), method.getName());
            }
        }

        final boolean hasDefault = method.isAnnotationPresent(Default.class);
        final boolean hasDefaultNull = method.isAnnotationPresent(DefaultNull.class);

        if (hasDefault && hasDefaultNull) {
            throw new IllegalArgumentException(String.format("@Default and @DefaultNull present in [%s]", method.toGenericString()));
        }

        boolean useMethod = false;

        if (value == null) {
            if (hasDefault) {
                value = method.getAnnotation(Default.class).value();

                logger.info("Assigning default value [{}] for {} on [{}#{}()]",
                        value, propertyNames, method.getDeclaringClass().getName(), method.getName());
            } else if (hasDefaultNull) {
                logger.info("Assigning null default value for {} on [{}#{}()]",
                        propertyNames, method.getDeclaringClass().getName(), method.getName());
            } else {
                // Final try: Is the method is actually callable?
                if (!Modifier.isAbstract(method.getModifiers())) {
                    useMethod = true;
                    logger.info("Using method itself for {} on [{}#{}()]",
                            propertyNames, method.getDeclaringClass().getName(), method.getName());
                } else {
                    throw new IllegalArgumentException(String.format("No value present for '%s' in [%s]",
                            prettyPrint(propertyNames, mappedReplacements),
                            method.toGenericString()));
                }
            }
        }

        if (useMethod) {
            return bbBuilder.method(ElementMatchers.is(method)).intercept(SuperMethodCall.INSTANCE);
        } else {
            if (value == null) {
                return bbBuilder.method(ElementMatchers.is(method)).intercept(FixedValue.nullValue());
            } else {
                final Object finalValue = bully.coerce(method.getGenericReturnType(), value, method.getAnnotation(Separator.class));
                return bbBuilder.method(ElementMatchers.is(method)).intercept(FixedValue.value(finalValue));
            }
        }
    }
}
