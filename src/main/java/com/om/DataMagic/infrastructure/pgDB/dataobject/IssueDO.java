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
 * Issue DO对象
 *
 * @author zhaoyan
 * @since 2025-01-17
 */
@Getter
@Setter
@TableName("fact_gitcode_issue")
public class IssueDO {
    @Serial
    private static final long serialVersionUID = 1L;

    @TableId
    private String uuid;

    @TableField("id")
    private String id;

    @TableField("url")
    private String url;

    @TableField("number")
    private String number;

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

    @TableField("deadline")
    private OffsetDateTime deadline;

    @TableField("plan_started_at")
    private OffsetDateTime planStartedAt;

    @TableField("user_id")
    private String userId;

    @TableField("user_login")
    private String userLogin;

    @TableField("state")
    private String state;

    @TableField("assignees_user_ids")
    private String assigneesUserIds;

    @TableField("labels_id")
    private String labelsId;

    @TableField("labels_name")
    private String labelsName;

    @TableField("issue_state")
    private String issueState;

    @TableField("issue_type")
    private String issueType;

    @TableField("repo_id")
    private String repoId;

    @TableField("repo_name")
    private String repoName;

    @TableField("repo_path")
    private String repoPath;

    @TableField("namespace")
    private String namespace;

    @TableField("priority")
    private String priority;

    @TableField("program")
    private String program;

    @TableField("security_hole")
    private String securityHole;

    @TableField("code_platform")
    private String codePlatform;
}
