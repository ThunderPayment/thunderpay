package org.thunderpay.commons.utils.reflect;

import javax.annotation.CheckForNull;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

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

        if (args.length == 1 && "equals".equals(method.getName()) && method.getParameterTypes()[0] == Object.class) {
            final Object arg = args[0];

            if (arg == null) {
                return false;
            }

            if (proxy == arg) {
                return true;
            }

            return isProxyOfSameInterface(arg, proxy.getClass()) && equals(Proxy.getInvocationHandler(arg));
        }

        if (args.length == 0 && "toString".equals(method.getName())) {
            return toString();
        }

        return handleInvocation(proxy, method, args);
    }

    @CheckForNull
    protected abstract Object handleInvocation(Object proxy, Method method, Object[] args) throws Throwable;


    private static boolean isProxyOfSameInterface(final Object arg, final Class<?> proxyClass) {
        return proxyClass.isInstance(arg) || (Proxy.isProxyClass(arg.getClass())) && Arrays.equals(arg.getClass().getInterfaces(), proxyClass.getInterfaces());
    }
}
