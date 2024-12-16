/**
 * @file ValidationError.java
 * @author Nuke Williams
 * @brief Validaton Errors
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Nuke Williams
 *
 */

package org.thunderpay.xmlloader;

import java.util.ArrayList;
import org.slf4j.Logger;

public class ValidationErrors extends ArrayList<ValidationError> {

    private static final long serialVersionUID = 1L;

    public void add(final String description, final Class<?> objectType, final String objectName) {
        add(new ValidationError(description, objectType, objectName));
    }

    public void log(final Logger log) {
        for (final ValidationError error : this) {
            error.log(log);
        }
    }

    public String toString() {
        final StringBuilder builder = new StringBuilder();
        for (final ValidationError error : this) {
            builder.append(error.toString());
        }
        return builder.toString();
    }
}