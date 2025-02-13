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

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.om.DataMagic.client.codePlatform.gitee.GiteeService;
import com.om.DataMagic.common.config.TaskConfig;
import com.om.DataMagic.common.util.ObjectMapperUtil;
import com.om.DataMagic.common.constant.CodePlatformEnum;
import com.om.DataMagic.common.constant.GitCodeConstant;
import com.om.DataMagic.infrastructure.pgDB.converter.RepoConverter;
import com.om.DataMagic.infrastructure.pgDB.dataobject.RepoDO;
import com.om.DataMagic.infrastructure.pgDB.service.platform.RepoService;
import com.om.DataMagic.process.DriverManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * repo application service.
 *
 * @author zhaoyan
 * @since 2025-02-06
 */
@Component
public class GiteeRepoProcess implements DriverManager {
    /**
     * Logger for logging messages in App class.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(GiteeRepoProcess.class);
    /**
     * gitee service.
     */
    @Autowired
    private GiteeService service;

    /**
     * repo converter .
     */
    @Autowired
    private RepoConverter converter;

    /**
     * repo service.
     */
    @Autowired
    private RepoService repoService;

    /**
     * taskconfig .
     */
    @Autowired
    private TaskConfig config;

    /**
     * 执行 拉取并更新指定组织下仓库信息.
     */
    @Override
    public void run() {
        LOGGER.info("repo data collection starting...... ");
        String[] split = config.getOrgs().split(",");
        for (String orgName : split) {
            getRepoList(orgName);
        }
    }

    /**
     * 获取gitcode平台组织下仓库信息.
     *
     * @param orgName 组织
     */
    private void getRepoList(String orgName) {
        int page = 1;
        while (true) {
            String repoInfo = null;
            try {
                repoInfo = service.getRepoInfo(orgName, page);
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
                continue;
            }
            if (GitCodeConstant.NOT_FOUND_RESPONSE.equals(repoInfo)
                    || repoInfo.startsWith("IOException retry")
                    || GitCodeConstant.NULL_ARRAY_RESPONSE.equals(repoInfo)) {
                break;
            }
            page++;
            try {
                ArrayNode arrayNode = ObjectMapperUtil.toObject(ArrayNode.class, repoInfo);
                List<RepoDO> doList = converter.toDOList(arrayNode, CodePlatformEnum.GITEE);
                if (!doList.isEmpty()) {
                    repoService.saveOrUpdateBatch(doList);
                }
            } catch (Exception e) {
                LOGGER.error("pr data collection error >>> " + e.getMessage());
            }
        }
    }
}
