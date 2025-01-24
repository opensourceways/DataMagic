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
    /**
     * Serializable class with a defined serial version UID.
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * uuid of data.
     */
    @TableId()
    private String uuid;

    /**
     * Name of user.
     */
    @TableField("user_name")
    private String userName;

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
     * avatar_url of user.
     */
    @TableField("avatar_url")
    private String avatarUrl;

    /**
     * The date when user join  a company.
     */
    @TableField("start_date")
    private OffsetDateTime startDate;

    /**
     * The date when user leave  a company.
     */
    @TableField("end_date")
    private OffsetDateTime endDate;

    /**
     * The date when task start.
     */
    @TableField("synced_at")
    private OffsetDateTime syncedAt;

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
     * The internal or external attribute of user.
     */
    private String internal;

    /**
     * The email of a user.
     */
    private String email;

    /**
     * The sig role of a user.
     */
    private String role;

    /**
     * The platform of a user.
     */
    @TableField("code_platform")
    private String codePlatform;

}
