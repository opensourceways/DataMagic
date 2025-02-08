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

package com.om.DataMagic.common.constant;

import lombok.Getter;

/**
 * 代码平台枚举.
 *
 * @author zhaoyan
 * @since 2025-01-15
 */
@Getter
public enum CodePlatformEnum {
    /**
     * GITEE.
     */
    GITEE("1", "gitee"),
    /**
     * GITHUB.
     */
    GITHUB("2", "github"),
    /**
     * GITCODE.
     */
    GITCODE("3", "gitcode"),
    /**
     * GITLAB.
     */
    GITLAB("4", "gitlab");

    /**
     * 枚举value.
     */
    private final String value;

    /**
     * 枚举code.
     */
    private final String text;

    CodePlatformEnum(String value, String text) {
        this.value = value;
        this.text = text;
    }
}
