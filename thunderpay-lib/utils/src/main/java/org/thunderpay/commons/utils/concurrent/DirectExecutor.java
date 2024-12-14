package org.thunderpay.commons.utils.concurrent;

import java.util.concurrent.Executor;

public enum DirectExecutor implements Executor  {
    INSTANCE;

    @Override
    public void execute(final Runnable command) {
        command.run();
    }
}
