/**
 * @file MySqlGlobalLockerDao.java
 * @author Krisna Pranav
 * @version 1.0
 * @date 2025-03-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.locker.mysql;

import org.thunderpay.locker.GlobalLockDao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

public class MySqlGlobalLockerDao implements GlobalLockDao {
    
    @Override
    public boolean lock(final Connection connection, final String lockName, final long timeout) {
    }
}
