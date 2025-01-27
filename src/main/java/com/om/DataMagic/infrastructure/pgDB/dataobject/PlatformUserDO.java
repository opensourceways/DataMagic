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

import java.io.Serial;

import com.om.DataMagic.common.constant.TableConstant;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName(TableConstant.PLATFORM_USER)
public class PlatformUserDO {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * the data uuid.
     */
    @TableId()
    private String uuid;
    /**
     * username.
     */
    @TableField("user_name")
    private String userName;
    /**
     * login name.
     */
    @TableField("user_login")
    private String userLogin;
    /**
     * user id.
     */
    @TableField("user_id")
    private String userId;
    /**
     * user avatar url.
     */
    @TableField("avatar_url")
    private String avatarUrl;
    /**
     * user email.
     */
    private String email;
    /**
     * the data platform.
     */
    @TableField("code_platform")
    private String codePlatform;

}
