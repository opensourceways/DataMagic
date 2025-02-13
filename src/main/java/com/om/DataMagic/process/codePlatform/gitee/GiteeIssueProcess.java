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
import com.om.DataMagic.common.util.ObjectMapperUtil;
import com.om.DataMagic.common.constant.CodePlatformEnum;
import com.om.DataMagic.common.constant.GitCodeConstant;
import com.om.DataMagic.infrastructure.pgDB.converter.IssueConverter;
import com.om.DataMagic.infrastructure.pgDB.dataobject.IssueDO;
import com.om.DataMagic.infrastructure.pgDB.dataobject.RepoDO;
import com.om.DataMagic.infrastructure.pgDB.service.platform.IssueService;
import com.om.DataMagic.infrastructure.pgDB.service.platform.RepoService;
import com.om.DataMagic.process.DriverManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * issue application service.
 *
 * @author zhaoyan
 * @since 2025-02-06
 */
@Component
public class GiteeIssueProcess implements DriverManager {
    /**
     * Logger for logging messages in App class.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(GiteeIssueProcess.class);
    /**
     * gitee service.
     */
    @Autowired
    private GiteeService service;

    /**
     * issue converter.
     */
    @Autowired
    private IssueConverter converter;

    /**
     * repo service.
     */
    @Autowired
    private RepoService repoService;

    /**
     * issue service.
     */
    @Autowired
    private IssueService issueService;

    /**
     * process start .
     */
    @Override
    public void run() {
        LOGGER.info("issue data collection starting...... ");
        List<RepoDO> repoDOList = repoService.list();
        for (RepoDO repoDO : repoDOList) {
            getIssueList(repoDO);
        }
    }

    /**
     * 获取GitCode平台仓库下Issue信息.
     *
     * @param repoDO 仓库信息
     */
    private void getIssueList(RepoDO repoDO) {
        int page = 1;
        while (true) {
            if (page > GitCodeConstant.MAX_PAGE) {
                LOGGER.error("issue data collection big page " + ObjectMapperUtil.writeValueAsString(repoDO));
                break;
            }
            String issueInfo = null;
            try {
                issueInfo = service.getIssueInfo(repoDO.getNamespace(), repoDO.getPath(), page);
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
                continue;
            }
            if (GitCodeConstant.NOT_FOUND_RESPONSE.equals(issueInfo)
                    || issueInfo.startsWith("IOException retry")
                    || GitCodeConstant.NULL_ARRAY_RESPONSE.equals(issueInfo)) {
                break;
            }
            page++;
            try {
                ArrayNode arrayNode = ObjectMapperUtil.toObject(ArrayNode.class, issueInfo);
                List<IssueDO> doList = converter.toDOList(arrayNode, repoDO.getNamespace(), CodePlatformEnum.GITEE);
                if (!doList.isEmpty()) {
                    issueService.saveOrUpdateBatch(doList);
                }
            } catch (Exception e) {
                LOGGER.error("issue data collection error >>> " + e.getMessage());
            }
        }
    }
}
