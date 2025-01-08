/**
 * @file Pagination.java
 * @author Krisna Pranav
 * @brief Pagination
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.util.entity;

import java.io.Closeable;

public interface Pagination<T> extends Iterable<T>, Closeable {
    public Long getCurrentOffset();

    public Long getNextOffset();

    public Long getMaxNbRecords();

    public Long getTotalNbRecords();
}