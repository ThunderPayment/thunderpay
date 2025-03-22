/**
 * @file SubscriberRegistry.java
 * @author Krisna Pranav
 * @brief Subscriber Registry
 * @version 1.0
 * @date 2025-03-22
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.commons.eventbus;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;
import javax.annotation.CheckForNull;

import org.thunderpay.commons.utils.Preconditions;
import org.thunderpay.commons.utils.Primitives;
import org.thunderpay.commons.utils.TypeToken;
import org.thunderpay.commons.utils.annotation.VisibleForTesting;
import org.thunderpay.commons.utils.cache.Cache;
import org.thunderpay.commons.utils.cache.DefaultCache;
import org.thunderpay.commons.utils.cache.DefaultSynchronizedCache;
import org.thunderpay.commons.utils.collect.Iterators;
import org.thunderpay.commons.utils.collect.MultiValueHashMap;
import org.thunderpay.commons.utils.collect.MultiValueMap;

final class SubscriberRegistry {
    private final ConcurrentMap<Class<?>, CopyOnWriteArraySet<Subscriber>> subscribers = new ConcurrentHashMap<>();

    private final EventBus bus;

    SubscriberRegistry(final EventBus bus) {
        this.bus = Preconditions.checkNotNull(bus);
    }

    void register(final Object listener) {
        final MultiValueMap<Class<?>, Subscriber> listenerMethods = findAllSubscribers(listener);

        for (final Entry<Class<?>, List<Subscriber>> entry : listenerMethods.entrySet()) {
            final Class<?> eventType = entry.getKey();
            final Collection<Subscriber> eventMethodsInListener = entry.getValue();

            CopyOnWriteArraySet<Subscriber> eventSubscribers = subscribers.get(eventType);

            if (eventSubscribers == null) {
                final CopyOnWriteArraySet<Subscriber> newSet = new CopyOnWriteArraySet<>();
                eventSubscribers = Objects.requireNonNullElse(subscribers.putIfAbsent(eventType, newSet), newSet);
            }

            eventSubscribers.addAll(eventMethodsInListener);
        }
    }

    void unregister(final Object listener) {
        final MultiValueMap<Class<?>, Subscriber> listenerMethods = findAllSubscribers(listener);

        for (final Entry<Class<?>, List<Subscriber>> entry : listenerMethods.entrySet()) {
            final Class<?> eventType = entry.getKey();
            final Collection<Subscriber> listenerMethodsForType = entry.getValue();

            final CopyOnWriteArraySet<Subscriber> currentSubscribers = subscribers.get(eventType);
            if (currentSubscribers == null || !currentSubscribers.removeAll(listenerMethodsForType)) {
                throw new IllegalArgumentException("missing event subscriber for an annotated method. Is " + listener + " registered?");
            }
        }
    }

    @VisibleForTesting
    Set<Subscriber> getSubscribersForTesting(final Class<?> eventType) {
        return Objects.requireNonNullElse(subscribers.get(eventType), Collections.emptySet());
    }

    Iterator<Subscriber> getSubscribers(final Object event) {
        final Set<Class<?>> eventTypes = flattenHierarchy(event.getClass());

        final List<Iterator<Subscriber>> subscriberIterators = new ArrayList<>(eventTypes.size());

        for (final Class<?> eventType : eventTypes) {
            final CopyOnWriteArraySet<Subscriber> eventSubscribers = subscribers.get(eventType);
            if (eventSubscribers != null) {
                subscriberIterators.add(eventSubscribers.iterator());
            }
        }

        return Iterators.concat(subscriberIterators.iterator());
    }

    private static final Cache<Class<?>, List<Method>> subscriberMethodsCache = new DefaultSynchronizedCache<>(
            Integer.MAX_VALUE,
            DefaultCache.NO_TIMEOUT,
            SubscriberRegistry::getAnnotatedMethodsNotCached
    );

    private MultiValueMap<Class<?>, Subscriber> findAllSubscribers(final Object listener) {
        final MultiValueMap<Class<?>, Subscriber> methodsInListener = new MultiValueHashMap<>();
        final Class<?> clazz = listener.getClass();

        for (final Method method : subscriberMethodsCache.get(clazz)) {
            final Class<?>[] parameterTypes = method.getParameterTypes();
            final Class<?> eventType = parameterTypes[0];
            methodsInListener.putElement(eventType, Subscriber.create(bus, listener, method));
        }

        return methodsInListener;
    }

    private static List<Method> getAnnotatedMethodsNotCached(final Class<?> clazz) {
        final Set<? extends Class<?>> supertypes = TypeToken.getRawTypes(clazz);
        final Map<MethodIdentifier, Method> identifiers = new HashMap<>();
        for (final Class<?> supertype : supertypes) {
            for (final Method method : supertype.getDeclaredMethods()) {
                if (method.isAnnotationPresent(Subscribe.class) && !method.isSynthetic()) {
                    final Class<?>[] parameterTypes = method.getParameterTypes();
                    Preconditions.checkArgument(parameterTypes.length == 1,
                            "Method %s has @Subscribe annotation but has %s parameters. Subscriber methods must have exactly 1 parameter.",
                            method,
                            parameterTypes.length);

                    Preconditions.checkArgument(!parameterTypes[0].isPrimitive(),
                            "@Subscribe method %s's parameter is %s. Subscriber methods cannot accept primitives. Consider changing the parameter to %s.",
                            method,
                            parameterTypes[0].getName(),
                            Primitives.wrap(parameterTypes[0]).getSimpleName());

                    final MethodIdentifier ident = new MethodIdentifier(method);
                    if (!identifiers.containsKey(ident)) {
                        identifiers.put(ident, method);
                    }
                }
            }
        }
        return List.copyOf(identifiers.values());
    }

    private static final Cache<Class<?>, Set<Class<?>>> flattenHierarchyCache = new DefaultSynchronizedCache<>(
            Integer.MAX_VALUE,
            DefaultCache.NO_TIMEOUT,

            key -> new LinkedHashSet<>(TypeToken.getRawTypes(key)));

    @VisibleForTesting
    static Set<Class<?>> flattenHierarchy(final Class<?> concreteClass) {
        return flattenHierarchyCache.get(concreteClass);
    }

    private static final class MethodIdentifier {

        private final String name;
        private final List<Class<?>> parameterTypes;

        MethodIdentifier(final Method method) {
            this.name = method.getName();
            this.parameterTypes = Arrays.asList(method.getParameterTypes());
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, parameterTypes);
        }

        @Override
        public boolean equals(@CheckForNull final Object o) {
            if (o instanceof MethodIdentifier) {
                final MethodIdentifier ident = (MethodIdentifier) o;
                return name.equals(ident.name) && parameterTypes.equals(ident.parameterTypes);
            }
            return false;
        }
    }
}