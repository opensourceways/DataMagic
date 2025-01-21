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

package com.om.DataMagic.common.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;

/**
 * LocalDateTime 日期工具测试类
 *
 * @author zhaoyan
 * @since 2025-01-16
 */
@ExtendWith(MockitoExtension.class)
public class DateUtilTest {
    @Test
    @DisplayName("根据id查询软件成功")
    void testParseSuccess() {
        String dateStr = "2024-12-26T15:47:50+08:00";
        OffsetDateTime parse = DateUtil.parse(dateStr);
        Assertions.assertNotNull(parse);
        Assertions.assertEquals(2024, parse.getYear());
        Assertions.assertEquals(12, parse.getMonthValue());
        Assertions.assertEquals(26, parse.getDayOfMonth());
    }

    @Test
    @DisplayName("根据id查询软件成功")
    void testParseSuccessNull() {
        OffsetDateTime parse = DateUtil.parse(null);
        Assertions.assertNull(parse);
    }
}
