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

package com.om.DataMagic.infrastructure.pgDB.dataobject;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.time.OffsetDateTime;

/**
 * 仓库事实表 对象
 *
 * @author zhaoyan
 * @since 2025-01-20
 */
@Getter
@Setter
@TableName("fact_gitcode_repo")
public class RepoDO {
    @Serial
    private static final long serialVersionUID = 1L;

    @TableId
    private String uuid;

    @TableField("id")
    private String id;

    @TableField("name")
    private String name;

    @TableField("path")
    private String path;

    @TableField("body")
    private String body;

    @TableField("created_at")
    private OffsetDateTime createdAt;

    @TableField("updated_at")
    private OffsetDateTime updatedAt;

    @TableField("pushed_at")
    private OffsetDateTime pushedAt;

    @TableField("html_url")
    private String htmlUrl;

    @TableField("status")
    private String status;

    @TableField("user_login")
    private String userLogin;

    @TableField("private")
    private String isPrivate;

    @TableField("public")
    private String isPublic;

    @TableField("internal")
    private String isInternal;

    @TableField("fork")
    private String isFork;

    @TableField("namespace")
    private String namespace;

    @TableField("code_platform")
    private String codePlatform;
}
