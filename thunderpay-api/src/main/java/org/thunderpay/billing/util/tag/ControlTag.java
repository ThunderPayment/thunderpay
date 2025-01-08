/**
 * @file ControlTag.java
 * @author Krisna Pranav
 * @brief Control Tag
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.util.tag;

public interface ControlTag extends Tag {
    public ControlTagType getControlTagType();
}
