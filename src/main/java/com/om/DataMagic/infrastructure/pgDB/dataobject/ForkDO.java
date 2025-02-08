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

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.om.DataMagic.common.constant.TableConstant;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.time.OffsetDateTime;

@Getter
@Setter
@TableName(TableConstant.FORK)
public class ForkDO {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * uuid of data.
     */
    @TableId(type = IdType.AUTO)
    private String uuid;
    /**
     * the fork data id.
     */
    @TableField("id")
    private String id;
    /**
     * the fork user name.
     */
    @TableField("user_name")
    private String userName;
    /**
     * the fork user id.
     */
    @TableField("user_id")
    private String userId;
    /**
     * the fork user's login name.
     */
    @TableField("user_login")
    private String userLogin;
    /**
     * the repo url.
     */
    @TableField("url")
    private String url;
    /**
     * the repo full name.
     */
    @TableField("full_name")
    private String fullName;
    /**
     * the repo namespace.
     */
    @TableField("namespace")
    private String namespace;
    /**
     * the repo name.
     */
    @TableField("repo_name")
    private String repoName;
    /**
     * the repo path.
     */
    @TableField("repo_path")
    private String repoPath;
    /**
     * the create time of fork.
     */
    @TableField("created_at")
    private OffsetDateTime createdAt;
    /**
     * the update time of fork.
     */
    @TableField("updated_at")
    private OffsetDateTime updatedAt;
    /**
     * the push time of fork.
     */
    @TableField("pushed_at")
    private OffsetDateTime pushedAt;
    /**
     * the data platform.
     */
    @TableField("code_platform")
    private String codePlatform;

}
