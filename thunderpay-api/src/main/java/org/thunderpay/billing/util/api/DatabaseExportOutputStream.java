/**
 * @file DatabaseExportOutputStream.java
 * @author Krisna Pranav
 * @brief Database Export Output Stream
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.util.api;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface DatabaseExportOutputStream {
    public void newTable(String tableName, List<ColumnInfo> columnsForTable);
    
    public void write(Map<String, Object> row) throws IOException;
}