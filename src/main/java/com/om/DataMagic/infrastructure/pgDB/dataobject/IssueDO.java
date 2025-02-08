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
 * Issue DO对象.
 *
 * @author zhaoyan
 * @since 2025-01-17
 */
@Getter
@Setter
@TableName(TableConstant.ISSUE)
public class IssueDO {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * the data uuid.
     */
    @TableId
    private String uuid;
    /**
     * the issue id.
     */
    @TableField("id")
    private String id;
    /**
     * the issue url.
     */
    @TableField("url")
    private String url;
    /**
     * the issue number.
     */
    @TableField("number")
    private String number;
    /**
     * the issue html url.
     */
    @TableField("html_url")
    private String htmlUrl;
    /**
     * the issue title.
     */
    @TableField("title")
    private String title;
    /**
     * the issue title.
     */
    @TableField("body")
    private String body;
    /**
     * the create time of issue.
     */
    @TableField("created_at")
    private OffsetDateTime createdAt;
    /**
     * the update time of issue.
     */
    @TableField("updated_at")
    private OffsetDateTime updatedAt;
    /**
     * the close time of issue.
     */
    @TableField("closed_at")
    private OffsetDateTime closedAt;
    /**
     * the deadline of issue.
     */
    @TableField("deadline")
    private OffsetDateTime deadline;
    /**
     * the resolve plan of issue start at.
     */
    @TableField("plan_started_at")
    private OffsetDateTime planStartedAt;
    /**
     * the issue user id.
     */
    @TableField("user_id")
    private String userId;
    /**
     * the issue user login name.
     */
    @TableField("user_login")
    private String userLogin;
    /**
     * the issue state.
     */
    @TableField("state")
    private String state;
    /**
     * the issue assignees_user_ids.
     */
    @TableField("assignees_user_ids")
    private String assigneesUserIds;
    /**
     * the issue labelsId.
     */
    @TableField("labels_id")
    private String labelsId;
    /**
     * the issue labelsName.
     */
    @TableField("labels_name")
    private String labelsName;
    /**
     * the issue state.
     */
    @TableField("issue_state")
    private String issueState;
    /**
     * the type of issue .
     */
    @TableField("issue_type")
    private String issueType;
    /**
     * the repo id.
     */
    @TableField("repo_id")
    private String repoId;
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
     * the repo namespace.
     */
    @TableField("namespace")
    private String namespace;
    /**
     * the priority of.
     */
    @TableField("priority")
    private String priority;
    /**
     * the issue program.
     */
    @TableField("program")
    private String program;
    /**
     * the issue security_hole.
     */
    @TableField("security_hole")
    private String securityHole;
    /**
     * the data platform.
     */
    @TableField("code_platform")
    private String codePlatform;

    /**
     * the issue isRemoved.
     */
    @TableField("is_removed")
    private String isRemoved;
}
