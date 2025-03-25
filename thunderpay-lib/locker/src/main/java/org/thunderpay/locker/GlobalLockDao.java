/**
 * @file GlobalLockDao.java
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

public interface GlobalLockDao {

    boolean lock(final Connection connection, final String lockName, final long timeout, final TimeUnit timeUnit) throws SQLException;

    boolean releaseLock(final Connection connection, final String lockName) throws SQLException;

    boolean isLockFree(final Connection connection, final String lockName) throws SQLException;
}