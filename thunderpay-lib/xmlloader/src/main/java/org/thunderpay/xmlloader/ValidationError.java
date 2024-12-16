/**
 * @file ValidationError.java
 * @author Nuke Williams
 * @brief Validaton Error
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Nuke Williams
 *
 */

package org.thunderpay.xmlloader;

import org.slf4j.Logger;

public class ValidationError {

    private final String description;
    private final Class<?> objectType;
    private final String objectName;

    public ValidationError(final String description, final Class<?> objectType, final String objectName) {
        super();
        this.description = description;
        this.objectType = objectType;
        this.objectName = objectName;
    }

    public String getDescription() {
        return description;
    }

    public Class<?> getObjectType() {
        return objectType;
    }

    public String getObjectName() {
        return objectName;
    }

    public void log(final Logger log) {
        log.error(String.format("%s (%s:%s)", description, objectType, objectName));
    }

    public String toString() {
        return String.format("%s (%s:%s)%n", description, objectType, objectName);
    }
}