/**
 * @file GlobalLockBase.java
 * @author Krisna Pranav
 * @version 1.0
 * @date 2025-03-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.locker;

import java.sql.Connection;
import java.sql.SQLException;
import org.thunderpay.commons.profiling.Profiling;
import org.thunderpay.commons.profiling.Profiling.WithProfilingCallback;
import org.thunderpay.commons.profiling.ProfilingFeature.ProfilingFeatureType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GlobalLockBase implements GlobalLock {


    private static final Logger logger = LoggerFactory.getLogger(GlobalLockBase.class);

    private final GlobalLockDao lockDao;

    private final Connection connection;
    private final String lockName;
    private final ResetReentrantLockCallback resetCallback;
    private final Profiling<Void, RuntimeException> prof;

    public GlobalLockBase(final Connection connection, final String lockName, final GlobalLockDao lockDao, final ResetReentrantLockCallback resetCallback) {
        this.lockDao = lockDao;
        this.connection = connection;
        this.lockName = lockName;
        this.resetCallback = resetCallback;
        this.prof = new Profiling<>();
    }

    @Override
    public void release() {
        prof.executeWithProfiling(ProfilingFeatureType.GLOCK, "release", new WithProfilingCallback<Void, RuntimeException>() {
            @Override
            public Void execute() throws RuntimeException {

                if (resetCallback != null && !resetCallback.reset(lockName)) {
                    return null;
                }
                try {
                    lockDao.releaseLock(connection, lockName);
                } catch (final SQLException e) {
                    logger.warn("Unable to release lock for " + lockName, e);
                } finally {
                    try {
                        connection.close();
                    } catch (final SQLException e) {
                        logger.warn("Unable to close connection", e);
                    }
                }
                return null;
            }
        });
    }
}