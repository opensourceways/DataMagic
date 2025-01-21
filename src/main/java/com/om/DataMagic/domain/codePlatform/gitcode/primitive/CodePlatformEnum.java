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

package com.om.DataMagic.domain.codePlatform.gitcode.primitive;

import lombok.Getter;

/**
 * 代码平台枚举
 *
 * @author zhaoyan
 * @since 2025-01-15
 */
@Getter
public enum CodePlatformEnum {
    GITEE("1", "gitee"),
    GITHUB("2", "github"),
    GITCODE("3", "gitcode"),
    GITLAB("4", "gitlab");

    private final String value;

    private final String text;

    CodePlatformEnum(String value, String text) {
        this.value = value;
        this.text = text;
    }
}
