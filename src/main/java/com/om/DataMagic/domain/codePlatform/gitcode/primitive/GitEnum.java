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
 * git基本对象枚举
 *
 * @author zhaoyan
 * @since 2025-01-17
 */
@Getter
public enum GitEnum {

    /**
     * 评论类型
     */
    COMMENT_PR("pr_comment","pr评论"),
    COMMENT_ISSUE("issue_comment","issue评论"),
    /**
     * issue网页地址模板， 1-所属者，2-仓库，3-issue number
     */
    ISSUE_URL_TEMPLATE("https://gitcode.com/%s/%s/issues/%s","issue网页地址模板"),

    COMMENT("comment","评论");

    private final String value;

    private final String text;

    GitEnum(String value, String text) {
        this.value = value;
        this.text = text;
    }
}
