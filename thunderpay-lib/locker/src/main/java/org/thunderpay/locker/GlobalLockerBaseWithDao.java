/**
 * @file GlobalLockerBaseWithDao.java
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
import java.util.concurrent.TimeUnit;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class GlobalLockerBaseWithDao extends GlobalLockerBase {

    private static final Logger logger = LoggerFactory.getLogger(GlobalLockerBaseWithDao.class);

    protected final GlobalLockDao globalLockDao;

    private final DataSource dataSource;

    public GlobalLockerBaseWithDao(final DataSource dataSource, final GlobalLockDao globalLockDao, final long timeout, final TimeUnit timeUnit) {
        super(timeout, timeUnit);
        this.dataSource = dataSource;
        this.globalLockDao = globalLockDao;
    }

    @Override
    public boolean isFree(final String service, final String lockKey) {
        final String lockName = getLockName(service, lockKey);

        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            return globalLockDao.isLockFree(connection, lockName);
        } catch (final SQLException e) {
            logger.warn("Unable to check if lock is free", e);
            return false;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (final SQLException e) {
                    logger.warn("Unable to close connection", e);
                }
            }
        }
    }

    @Override
    protected GlobalLock doLock(final String lockName) {
        Connection connection = null;
        boolean obtained = false;
        try {
            connection = dataSource.getConnection();
            obtained = globalLockDao.lock(connection, lockName, timeout, timeUnit);
            if (obtained) {
                final GlobalLock lock = getGlobalLock(connection, lockName, new ResetReentrantLockCallback() {
                    @Override
                    public boolean reset(final String lockName) {
                        return lockTable.releaseLock(lockName);
                    }
                });
                lockTable.createLock(lockName, lock);
                return lock;
            }
        } catch (final SQLException e) {
            logger.warn("Unable to obtain lock for {}", lockName, e);
        } finally {
            if (!obtained && connection != null) {
                try {
                    connection.close();
                } catch (final SQLException e) {
                    logger.warn("Unable to close connection", e);
                }
            }
        }
        return null;
    }

    protected abstract GlobalLock getGlobalLock(final Connection connection, final String lockName, final ResetReentrantLockCallback resetCb);
}