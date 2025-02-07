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
 * Comment DO对象.
 *
 * @author zhaoyan
 * @since 2025-01-17
 */
@Getter
@Setter
@TableName(TableConstant.COMMENT)
public class CommentDO {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * uuid .
     */
    @TableId
    private String uuid;

    /**
     * comment is.
     */
    @TableField("id")
    private String id;

    /**
     * comment html url.
     */
    @TableField("html_url")
    private String htmlUrl;

    /**
     * content of comment.
     */
    @TableField("body")
    private String body;

    /**
     * the create time of comment.
     */
    @TableField("created_at")
    private OffsetDateTime createdAt;

    /**
     * the update time of comment.
     */
    @TableField("updated_at")
    private OffsetDateTime updatedAt;

    /**
     * the user id of comment.
     */
    @TableField("user_id")
    private String userId;

    /**
     * the comment user's login name.
     */
    @TableField("user_login")
    private String userLogin;

    /**
     * the comment type.
     */
    @TableField("comment_type")
    private String commentType;

    /**
     * the platform of comment.
     */
    @TableField("code_platform")
    private String codePlatform;

    /**
     * the tag url of comment.
     */
    @TableField("tag_url")
    private String tagUrl;

    /**
     * is self.
     */
    @TableField("self")
    private String isSelf;

    /**
     * the repo namespace.
     */
    private String namespace;

    /**
     * the repo path.
     */
    @TableField("repo_path")
    private String repoPath;
}
