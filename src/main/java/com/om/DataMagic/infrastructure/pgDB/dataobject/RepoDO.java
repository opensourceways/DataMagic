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
import com.om.DataMagic.common.constant.TableConstant;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.time.OffsetDateTime;

/**
 * 仓库 PO 对象.
 *
 * @author zhaoyan
 * @since 2025-01-14
 */
@Getter
@Setter
@TableName(TableConstant.REPO)
public class RepoDO {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * the repo id.
     */
    @TableId
    private String uuid;
    /**
     * the repo id.
     */
    private String id;
    /**
     * the repo name.
     */
    private String name;
    /**
     * the repo path.
     */
    private String path;
    /**
     * the repo body.
     */
    private String body;
    /**
     * html url.
     */
    @TableField("html_url")
    private String htmlUrl;
    /**
     * the repo create time.
     */
    @TableField("created_at")
    private OffsetDateTime createdAt;
    /**
     * the repo update time.
     */
    @TableField("updated_at")
    private OffsetDateTime updatedAt;
    /**
     * the repo push time.
     */
    @TableField("pushed_at")
    private OffsetDateTime pushedAt;
    /**
     * the repo status.
     */
    private String status;
    /**
     * user login name.
     */
    @TableField("user_login")
    private String userLogin;
    /**
     * whether the repo is private.
     */
    @TableField("private")
    private String isPrivate;
    /**
     * whether the repo is public.
     */
    @TableField("public")
    private String isPublic;
    /**
     * whether the repo is internal.
     */
    private String internal;
    /**
     * whether the repo is fork.
     */
    private String fork;
    /**
     * owner of repo: user accounts or organizations.
     */
    private String namespace;
    /**
     * code platform.
     */
    @TableField("code_platform")
    private String codePlatform;
}
