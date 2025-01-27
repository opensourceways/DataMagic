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

import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

/**
 * 日期处理工具.
 *
 * @author zhaoyan
 * @since 2025-01-16
 */
public final class DateUtil {

    // Private constructor to prevent instantiation of the utility class
    private DateUtil() {
        throw new AssertionError("DateUtil class cannot be instantiated.");
    }

    /**
     * @param dateStr 字符串日期.
     * @return 格式化后的日期
     */
    public static OffsetDateTime parse(String dateStr) {
        if (StringUtils.isEmpty(dateStr) || "null".equals(dateStr)) {
            return null;
        }
        return OffsetDateTime.parse(dateStr).withOffsetSameInstant(ZoneOffset.UTC);
    }

    /**
     * The function is to get duration between start and end.
     *
     * @param start The date of start
     * @param end   The date of end
     * @return The seconds of duration
     */
    public static Long getDuration(OffsetDateTime start, OffsetDateTime end) {
        if (null == start || null == end) {
            return null;
        }
        Duration duration = Duration.between(start, end);
        return duration.toSeconds();
    }

    /**
     * The function is to determine whether the date is between start and end.
     * closed interval in front, open interval in back
     *
     * @param cur   The date of cur
     * @param start The date of start
     * @param end   The date of end
     * @return boolean
     */
    public static boolean betweenDuration(OffsetDateTime cur, OffsetDateTime start, OffsetDateTime end) {
        if (null == start || null == end) {
            return false;
        }
        if ((cur.isEqual(start) || cur.isAfter(start)) && cur.isBefore(end)) {
            return true;
        }
        return false;
    }
}
