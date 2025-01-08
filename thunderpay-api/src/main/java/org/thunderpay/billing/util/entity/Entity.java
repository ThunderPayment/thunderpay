/**
 * @file Entity
 * @author Krisna Pranav
 * @brief Entity
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.util.entity;

import java.util.UUID;
import org.joda.time.DateTime;

public interface Entity {
    public UUID getId();

    public DateTime getCreatedDate();

    public DateTime getUpdatedData();
}
