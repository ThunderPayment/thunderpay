/**
 * @file CatalogValidation.java
 * @author Krisna Pranav
 * @brief Catalog Validation
 * @version 1.0
 * @date 2025-01-21
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.catalog.api;

import java.util.List;

public interface CatalogValidation {
    List<CatalogValidationError> getValidationError();
}
