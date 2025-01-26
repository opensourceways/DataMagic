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

package com.om.DataMagic.process.codePlatform.gitee;

import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.om.DataMagic.infrastructure.pgDB.dataobject.PlatformUserDO;
import com.om.DataMagic.infrastructure.pgDB.service.platform.PlatformUserService;
import com.om.DataMagic.process.DriverManager;

@Component
public class GiteeProcess implements DriverManager {

    /**
     * userservice.
     */
    @Autowired
    private PlatformUserService userService;

    /**
     * Logger for logging messages in App class.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(GiteeProcess.class);

    /**
     * pull userinfo.
     */
    @Override
    public void run() {
        Collection<PlatformUserDO> objList = new ArrayList<>();
        LOGGER.info("gitee");
        userService.saveOrUpdateBatch(objList);
    }

}
