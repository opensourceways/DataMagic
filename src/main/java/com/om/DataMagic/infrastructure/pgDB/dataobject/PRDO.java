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
 * pr DO对象
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

    @TableId
    private String uuid;

    @TableField("id")
    private String id;

    @TableField("number")
    private String number;

    @TableField("url")
    private String url;

    @TableField("html_url")
    private String htmlUrl;

    @TableField("title")
    private String title;

    @TableField("body")
    private String body;

    @TableField("created_at")
    private OffsetDateTime createdAt;

    @TableField("updated_at")
    private OffsetDateTime updatedAt;

    @TableField("closed_at")
    private OffsetDateTime closedAt;

    @TableField("merged_at")
    private OffsetDateTime mergedAt;

    @TableField("user_login")
    private String userLogin;

    @TableField("user_id")
    private String userId;

    @TableField("state")
    private String state;

    @TableField("assignees_user_ids")
    private String assigneesUserIds;

    @TableField("labels_id")
    private String labelsId;

    @TableField("labels_name")
    private String labelsName;

    @TableField("head_label")
    private String headLabel;

    @TableField("head_ref")
    private String headRef;

    @TableField("head_user_id")
    private String headUserId;

    @TableField("head_user_login")
    private String headUserLogin;

    @TableField("head_repo_id")
    private String headRepoId;

    @TableField("head_repo_path")
    private String headRepoPath;

    @TableField("head_repo_full_name")
    private String headRepoFullName;

    @TableField("base_label")
    private String baseLabel;

    @TableField("base_ref")
    private String baseRef;

    @TableField("base_user_id")
    private String baseUserId;

    @TableField("base_user_login")
    private String baseUserLogin;

    @TableField("base_repo_id")
    private String baseRepoId;

    @TableField("repo_name")
    private String repoName;

    @TableField("repo_path")
    private String repoPath;

    @TableField("base_owner_user_id")
    private String baseOwnerUserId;

    @TableField("base_owner_user_login")
    private String baseOwnerUserLogin;

    @TableField("private")
    private String isPrivate;

    @TableField("internal")
    private String internal;

    @TableField("fork")
    private String fork;

    @TableField("namespace")
    private String namespace;

    @TableField("is_removed")
    private String isRemoved;

    @TableField("code_platform")
    private String codePlatform;

    @TableField("added_lines")
    private Long addedLines;

    @TableField("removed_lines")
    private Long removedLines;
}
