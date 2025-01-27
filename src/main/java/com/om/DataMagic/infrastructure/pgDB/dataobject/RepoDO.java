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

/**
 * 仓库 PO 对象.
 *
 * @author zhaoyan
 * @since 2025-01-14
 */
@Getter
@Setter
@TableName("dim_repo")
public class RepoDO {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * the repo id.
     */
    @TableId
    private Integer repoId;
    /**
     * the repo name.
     */
    @TableField("repo_name")
    private String repoName;
    /**
     * the repo url.
     */
    @TableField("repo_url")
    private String repoUrl;
    /**
     * the owner of repo.
     */
    @TableField("owner")
    private String ownerName;
    /**
     * user id.
     */
    @TableField("user_id")
    private Integer userId;
    /**
     * sig name.
     */
    @TableField("sig_name")
    private String sigName;
}
