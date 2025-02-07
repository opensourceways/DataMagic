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

package com.om.DataMagic.infrastructure.pgDB.dataobject.dws;

import com.om.DataMagic.common.constant.TableConstant;

import java.io.Serial;
import java.time.OffsetDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName(TableConstant.DWS_CONTRIB)
public class ContributeDO {
    /**
     * Serializable class with a defined serial version UID.
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * uuid of data.
     */
    @TableId
    private String uuid;

    /**
     * Login of user.
     */
    @TableField("user_login")
    private String userLogin;

    /**
     * id of user.
     */
    @TableField("user_id")
    private String userId;

    /**
     * The internal or external attribute of user.
     */
    private String internal;

    /**
     * The sig role of a user.
     */
    private String role;

    /**
     * The type of a company.
     */
    @TableField("company_type")
    private String companyType;

    /**
     * The name of a company.
     */
    private String company;

    /**
     * The html url of a contribute.
     */
    @TableField("html_url")
    private String htmlUrl;

    /**
     * The repo path of a contribute.
     */
    @TableField("repo_path")
    private String repoPath;

    /**
     * The repo sig name of a contribute.
     */
    @TableField("sig_name")
    private String sigName;

    /**
     * The repo namespace of a contribute.
     */
    @TableField("namespace")
    private String namespace;

    /**
     * The platform of a user.
     */
    @TableField("code_platform")
    private String codePlatform;

    /**
     * The version of a contribute.
     */
    private String version;

    /**
     * The branch of a pr.
     */
    private String branch;

    /**
     * The title of a pr or issue.
     */
    @TableField("title")
    private String title;

    /**
     * The body of a comment.
     */
    @TableField("body")
    private String body;

    /**
     * The created time of a contribute.
     */
    @TableField("created_at")
    private OffsetDateTime createdAt;

    /**
     * The updated time of a contribute.
     */
    @TableField("updated_at")
    private OffsetDateTime updatedAt;

    /**
     * The closed time of a issue.
     */
    @TableField("closed_at")
    private OffsetDateTime closedAt;

    /**
     * The merged time of a pr.
     */
    @TableField("merged_at")
    private OffsetDateTime mergedAt;

    /**
     * The state of a pr or issue.
     */
    @TableField("state")
    private String state;

    /**
     * The type of a issue.
     */
    @TableField("issue_type")
    private String issueType;

    /**
     * The contribute type of a contribute.
     */
    @TableField("contrib_type")
    private String contribType;

    /**
     * The first reply time of a pr or issue.
     */
    @TableField("first_reply_time")
    private Long firstReplyTime;

    /**
     * The comment number of a pr or issue.
     */
    @TableField("comment_num")
    private Integer commentNum;

    /**
     * The closed duration time of a pr or issue.
     */
    @TableField("closed_time")
    private Long closedTime;

    /**
     * The comment type of a comment
     */
    @TableField("comment_type")
    private String commentType;

    /**
     * Indicate whether the contribute is a pr.
     */
    @TableField("is_pr")
    private Integer isPr;

    /**
     * Indicate whether the contribute is a issue.
     */
    @TableField("is_issue")
    private Integer isIssue;

    /**
     * Indicate whether the contribute is a comment.
     */
    @TableField("is_comment")
    private Integer isComment;

    /**
     * The add lines of a pr.
     */
    @TableField("add")
    private Long add;

    /**
     * The remove lines of a pr.
     */
    @TableField("remove")
    private Long remove;

    /**
     * the contribute isRemoved.
     */
    @TableField("is_removed")
    private String isRemoved;
}
