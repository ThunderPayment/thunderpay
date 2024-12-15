/**
 * @file Files.java
 * @author Krisna Pranav
 * @brief Files
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.commons.utils.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.attribute.FileAttribute;

public final class Files {
    public static File createTempDirectory() {
        try {
            return java.nio.file.Files.createTempDirectory(String.valueOf(System.currentTimeMillis())).toFile();
        } catch(final Exception e) {
            throw new RuntimeException("Cannot create temp directory: " + e.getMessage());
        }
    }
}
