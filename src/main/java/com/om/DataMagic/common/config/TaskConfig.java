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

package com.om.DataMagic.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties(prefix = "task")
@Setter
@Getter
public class TaskConfig {
    /**
     * orgs of task.
     */
    private String orgs;

    /**
     * Names of task.
     */
    private String tasks;

    /**
     * Platform of task.
     */
    private String platform;

    /**
     * Base api of platform.
     */
    private String baseApi;

    /**
     * Token of platform org.
     */
    private String token;

    /**
     * Robots of community.
     */
    private String robots;

    /**
     * Name of community.
     */
    private String community;
}
