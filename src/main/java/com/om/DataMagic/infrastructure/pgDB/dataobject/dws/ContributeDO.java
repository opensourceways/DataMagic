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
    @Serial
    private static final long serialVersionUID = 1L;

    @TableId
    private String uuid;

    @TableField("user_id")
    private String userId;

    @TableField("user_login")
    private String userLogin;

    private String role;

    @TableField("company")
    private String company;

    @TableField("company_type")
    private String companyType;

    @TableField("html_url")
    private String htmlUrl;

    @TableField("repo_path")
    private String repoPath;

    @TableField("sig_name")
    private String sigName;

    @TableField("namespace")
    private String namespace;

    @TableField("code_platform")
    private String codePlatform;

    @TableField("internal")
    private Integer internal;

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

    @TableField("state")
    private String state;

    @TableField("issue_type")
    private String issueType;

    @TableField("first_reply_time")
    private Long firstReplyTime;

    @TableField("comment_num")
    private Integer commentNum;

    @TableField("closed_time")
    private Long closedTime;

    @TableField("is_pr")
    private Integer isPr;

    @TableField("is_issue")
    private Integer isIssue;

    @TableField("is_comment")
    private Integer isComment;

    @TableField("add")
    private Long add;

    @TableField("remove")
    private Long remove;
}
