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
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.time.OffsetDateTime;


@Getter
@Setter
@TableName("fact_star")
public class StarDO {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * the data uuid.
     */
    @TableId(type = IdType.AUTO)
    private String uuid;
    /**
     * the star data id.
     */
    @TableField("id")
    private String id;
    /**
     * the star's user name.
     */
    @TableField("user_name")
    private String userName;
    /**
     * the star's login name.
     */
    @TableField("user_login")
    private String userLogin;
    /**
     * the star type.
     */
    @TableField("type")
    private String type;
    /**
     * the create time of star.
     */
    @TableField("created_at")
    private OffsetDateTime createdAt;
    /**
     * the repo namespace.
     */
    @TableField("namespace")
    private String namespace;
    /**
     * the repo path.
     */
    @TableField("repo_path")
    private String repoPath;
    /**
     * the repo fullpath.
     */
    @TableField("repo_full_name")
    private String repoFullName;
    /**
     * the data platform.
     */
    @TableField("code_platform")
    private String codePlatform;


}
