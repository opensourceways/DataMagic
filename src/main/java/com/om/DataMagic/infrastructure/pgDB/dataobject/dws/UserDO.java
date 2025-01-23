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
@TableName(TableConstant.DWS_USER)
public class UserDO {
    @Serial
    private static final long serialVersionUID = 1L;

    @TableId()
    private String uuid;
    
    @TableField("user_name")
    private String userName;

    @TableField("user_login")
    public String userLogin;

    @TableField("user_id")
    public String userId;

    @TableField("avatar_url")
    public String avatarUrl;

    @TableField("start_date")
    private OffsetDateTime startDate;

    @TableField("end_date")
    private OffsetDateTime endDate;

    @TableField("synced_at")
    private OffsetDateTime syncedAt;

    @TableField("company_type")
    private String companyType;

    public String company;

    public Integer internal;

    public String email;

    public String role;

    @TableField("code_platform")
    public String codePlatform;

    
}
