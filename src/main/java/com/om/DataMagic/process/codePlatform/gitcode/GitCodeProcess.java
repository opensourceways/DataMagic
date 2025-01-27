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

package com.om.DataMagic.process.codePlatform.gitcode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.om.DataMagic.client.codePlatform.gitcode.GitCodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.om.DataMagic.common.util.ObjectMapperUtil;
import com.om.DataMagic.infrastructure.pgDB.dataobject.PlatformUserDO;
import com.om.DataMagic.infrastructure.pgDB.service.platform.PlatformUserService;
import com.om.DataMagic.process.DriverManager;

@Component
public class GitCodeProcess implements DriverManager {

    /**
     * user service.
     */
    @Autowired
    private PlatformUserService userService;

    /**
     * gitcode service.
     */
    @Autowired
    private GitCodeService service;

    /**
     * Logger for logging messages in App class.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(GitCodeProcess.class);

    /**
     * running start here.
     */
    @Override
    public void run() {
        List<String> users = getUserList();
        saveData(users);
    }

    /**
     * Parese user api response string to object.
     *
     * @param userInfo The user api response content.
     * @return UserDO.
     */
    public PlatformUserDO parseUser(String userInfo) {
        JsonNode userJson = ObjectMapperUtil.toJsonNode(userInfo);
        PlatformUserDO user = new PlatformUserDO();
        user.setUserLogin(userJson.path("login").asText());
        user.setUserName(userJson.path("name").asText());
        user.setUserId(userJson.path("id").asText());
        user.setAvatarUrl(userJson.path("avatar_url").asText());
        user.setEmail(userJson.path("email").asText());
        user.setCodePlatform("gitcode");
        return user;
    }

    /**
     * Save data into db.
     *
     * @param users The list of user login
     * @return Result.
     */
    public boolean saveData(List<String> users) {
        boolean res = false;
        Collection<PlatformUserDO> userDetail = new ArrayList<>();
        for (String user : users) {
            String userInfo = service.getUserInfo(user);
            PlatformUserDO obj = parseUser(userInfo);
            userDetail.add(obj);
        }
        if (!userDetail.isEmpty()) {
            res = userService.saveOrUpdateBatch(userDetail);
        }
        return res;
    }

    /**
     * Get a list of user login.
     *
     * @return a list of user login.
     */
    public List<String> getUserList() {
        List<String> users = new ArrayList<>();
        return users;
    }

}
