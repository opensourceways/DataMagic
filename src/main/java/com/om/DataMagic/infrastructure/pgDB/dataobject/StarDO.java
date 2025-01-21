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
import java.util.Date;


@Getter
@Setter
@TableName("fact_star")
public class StarDO {
    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private String uuid;

    @TableField("id")
    private String id;

    @TableField("user_name")
    private String userName;

    @TableField("user_login")
    public String userLogin;

    @TableField("type")
    public String type;

    @TableField("created_at")
    public Date createdAt;

    @TableField("namespace")
    public String namespace;

    @TableField("repo_path")
    public String repoPath;

    @TableField("repo_full_name")
    public String repoFullName;

    @TableField("code_platform")
    public String codePlatform;

    public StarDO() {
    }
}
