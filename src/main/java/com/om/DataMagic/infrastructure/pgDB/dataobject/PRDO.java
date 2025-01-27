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
 * pr DO对象.
 *
 * @author zhaoyan
 * @since 2025-01-14
 */
@Getter
@Setter
@TableName("fact_gitcode_pr")
public class PRDO {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * the data uuid.
     */
    @TableId
    private String uuid;
    /**
     * the pr id.
     */
    @TableField("id")
    private String id;
    /**
     * the pr number.
     */
    @TableField("number")
    private String number;
    /**
     * the pr url.
     */
    @TableField("url")
    private String url;
    /**
     * the pr html_url.
     */
    @TableField("html_url")
    private String htmlUrl;
    /**
     * the pr title.
     */
    @TableField("title")
    private String title;
    /**
     * the pr body.
     */
    @TableField("body")
    private String body;
    /**
     * the create time of pr.
     */
    @TableField("created_at")
    private OffsetDateTime createdAt;
    /**
     * the upate time of pr.
     */
    @TableField("updated_at")
    private OffsetDateTime updatedAt;
    /**
     * the close time of pr.
     */
    @TableField("closed_at")
    private OffsetDateTime closedAt;
    /**
     * the merge time of pr.
     */
    @TableField("merged_at")
    private OffsetDateTime mergedAt;
    /**
     * the pr user login name.
     */
    @TableField("user_login")
    private String userLogin;
    /**
     * the pr user id.
     */
    @TableField("user_id")
    private String userId;
    /**
     * the pr state.
     */
    @TableField("state")
    private String state;
    /**
     * the pr assignees_user_ids.
     */
    @TableField("assignees_user_ids")
    private String assigneesUserIds;
    /**
     * the pr labels_id.
     */
    @TableField("labels_id")
    private String labelsId;
    /**
     * the pr labels_name.
     */
    @TableField("labels_name")
    private String labelsName;
    /**
     * the pr head_label.
     */
    @TableField("head_label")
    private String headLabel;
    /**
     * the pr head_ref.
     */
    @TableField("head_ref")
    private String headRef;
    /**
     * the pr head_user_id.
     */
    @TableField("head_user_id")
    private String headUserId;
    /**
     * the pr head_user_login.
     */
    @TableField("head_user_login")
    private String headUserLogin;
    /**
     * the pr head_repo_id.
     */
    @TableField("head_repo_id")
    private String headRepoId;
    /**
     * the pr head_repo_path.
     */
    @TableField("head_repo_path")
    private String headRepoPath;
    /**
     * the pr head_repo_full_name.
     */
    @TableField("head_repo_full_name")
    private String headRepoFullName;
    /**
     * the pr base_label.
     */
    @TableField("base_label")
    private String baseLabel;
    /**
     * the pr base_ref.
     */
    @TableField("base_ref")
    private String baseRef;
    /**
     * the pr base_user_id.
     */
    @TableField("base_user_id")
    private String baseUserId;
    /**
     * the pr base_user_login.
     */
    @TableField("base_user_login")
    private String baseUserLogin;
    /**
     * the pr base_repo_id.
     */
    @TableField("base_repo_id")
    private String baseRepoId;
    /**
     * the pr repo_name.
     */
    @TableField("repo_name")
    private String repoName;
    /**
     * the pr repo_path.
     */
    @TableField("repo_path")
    private String repoPath;
    /**
     * the pr base_owner_user_id.
     */
    @TableField("base_owner_user_id")
    private String baseOwnerUserId;
    /**
     * the pr base_owner_user_login.
     */
    @TableField("base_owner_user_login")
    private String baseOwnerUserLogin;
    /**
     * the pr is private.
     */
    @TableField("private")
    private String isPrivate;
    /**
     * the pr internal.
     */
    @TableField("internal")
    private String internal;
    /**
     * the pr fork.
     */
    @TableField("fork")
    private String fork;
    /**
     * the repo namespace.
     */
    @TableField("namespace")
    private String namespace;
    /**
     * the pr isRemoved.
     */
    @TableField("is_removed")
    private String isRemoved;
    /**
     * the data codePlatform.
     */
    @TableField("code_platform")
    private String codePlatform;
    /**
     * the pr added_lines.
     */
    @TableField("added_lines")
    private Long addedLines;
    /**
     * the pr removed_lines.
     */
    @TableField("removed_lines")
    private Long removedLines;
}
