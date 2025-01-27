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

package com.om.DataMagic.common.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.om.DataMagic.common.config.TaskConfig;
import com.om.DataMagic.common.handler.PlatformTableNameHandler;

@Aspect
@Component
public class TableNameAspect {

    /**
     * Autowired config for task.
     */
    @Autowired
    private TaskConfig config;

    /**
     * Add the platform table name before executing sql operations.
     */
    @Before("execution(* com.om.DataMagic.infrastructure.pgDB.service.platform.*.*(..))")
    public void beforeExecPlatform() {
        PlatformTableNameHandler.setName(config.getPlatform());
    }

    /**
     * Remove the platform table name before executing sql operations.
     */
    @After("execution(* com.om.DataMagic.infrastructure.pgDB.service.platform.*.*(..))")
    public void afterExecPlatform() {
        PlatformTableNameHandler.removeName();
    }

    /**
     * Add the community table name before executing sql operations.
     */
    @Before("execution(* com.om.DataMagic.infrastructure.pgDB.service.dws.*.*(..))")
    public void beforeExecCommunity() {
        PlatformTableNameHandler.setName(config.getCommunity());
    }

    /**
     * Remove the community table name before executing sql operations.
     */
    @After("execution(* com.om.DataMagic.infrastructure.pgDB.service.dws.*.*(..))")
    public void afterExecCommunity() {
        PlatformTableNameHandler.removeName();
    }
}
