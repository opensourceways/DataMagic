/*
 This project is licensed under the Mulan PSL v2.
 You can use this software according to the terms and conditions of the Mulan PSL v2.
 You may obtain a copy of Mulan PSL v2 at:
     http://license.coscl.org.cn/MulanPSL2
 THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND,
 EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT,
 MERCHANTABILITY OR FIT FOR A PARTICULAR PURPOSE.
 See the Mulan PSL v2 for more details.
 Created: 2025
*/

package com.om.DataMagic.common.handler;

import java.util.Arrays;
import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.handler.TableNameHandler;

public class PlatformTableNameHandler implements TableNameHandler {

    /**
     * A list of table names.
     */
    private List<String> tableNames;

    /**
     * Constructor dynamic table name processor.
     * 
     * @param tableNames tableNames parameter
     * @return PlatformTableNameHandler.
     */
    public PlatformTableNameHandler(String... tableNames) {
        this.tableNames = Arrays.asList(tableNames);
    }

    /**
     * A ThreadLocal to avoid multi-thread conflicts.
     */
    private static final ThreadLocal<String> TABLE_NAME = new ThreadLocal<>();

    /**
     * Set a table name.
     * 
     * @param paltform table name parameter
     */
    public static void setName(String paltform) {
        TABLE_NAME.set(paltform);
    }

    /**
     * Remove a table name.
     */
    public static void removeName() {
        TABLE_NAME.remove();
    }

    /**
     * Set dynamic table name.
     * 
     * @param sql       sql string
     * @param tableName table name parameter
     * @return dynamic table name.
     */
    @Override
    public String dynamicTableName(String sql, String tableName) {
        if (this.tableNames.contains(tableName)) {
            return tableName + "_" + TABLE_NAME.get();
        } else {
            return tableName;
        }
    }

}
