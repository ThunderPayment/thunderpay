/**
 * @file AbstractInvocationHandler.java
 * @author Krisna Pranav
 * @version 1.0
 * @date 2025-03-22
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */


package org.thunderpay.commons.utils.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

import javax.annotation.CheckForNull;

public abstract class AbstractInvocationHandler implements InvocationHandler {

    private static final Object[] NO_ARGS = {};

    @Override
    @CheckForNull
    public final Object invoke(final Object proxy, final Method method, @CheckForNull Object[] args) throws Throwable {
        if (args == null) {
            args = NO_ARGS;
        }
        if (args.length == 0 && "hashCode".equals(method.getName())) {
            return hashCode();
        }
        if (args.length == 1
                && "equals".equals(method.getName())
                && method.getParameterTypes()[0] == Object.class) {
            final Object arg = args[0];
            if (arg == null) {
                return false;
            }
            if (proxy == arg) {
                return true;
            }
            return isProxyOfSameInterfaces(arg, proxy.getClass())
                    && equals(Proxy.getInvocationHandler(arg));
        }
        if (args.length == 0 && "toString".equals(method.getName())) {
            return toString();
        }
        return handleInvocation(proxy, method, args);
    }

    @CheckForNull
    protected abstract Object handleInvocation(Object proxy, Method method, Object[] args) throws Throwable;

    @Override
    public boolean equals(@CheckForNull final Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    private static boolean isProxyOfSameInterfaces(final Object arg, final Class<?> proxyClass) {
        return proxyClass.isInstance(arg)
                || (Proxy.isProxyClass(arg.getClass())
                && Arrays.equals(arg.getClass().getInterfaces(), proxyClass.getInterfaces()));
    }
}
