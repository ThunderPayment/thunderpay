/**
 * @file ValidationException.java
 * @author Nuke Williams
 * @brief Validation Exceptions
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.xmlloader;

import java.io.PrintStream;

public class ValidationException extends Exception {
    private final ValidationErrors errors;

    ValidationException(final ValidationError errors) {
        this.errors = errors;
    }

    public ValidationErrors getErrors() {
        return errors;
    }

    @Override
    public void printStackTrace(final PrintStream arg0) {
        arg0.print(errors.toString());
        super.printStackTrace(arg0);
    }
}