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
import com.om.DataMagic.infrastructure.pgDB.converter.PRConverter;
import com.om.DataMagic.infrastructure.pgDB.dataobject.PRDO;
import com.om.DataMagic.infrastructure.pgDB.dataobject.RepoDO;
import com.om.DataMagic.infrastructure.pgDB.service.platform.PRService;
import com.om.DataMagic.infrastructure.pgDB.service.platform.RepoService;
import com.om.DataMagic.process.DriverManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * pr application service.
 *
 * @author zhaoyan
 * @since 2025-02-06
 */
@Component
public class GiteePRProcess implements DriverManager {
    /**
     * Logger for logging messages in App class.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(GiteePRProcess.class);
    /**
     * gitee service.
     */
    @Autowired
    private GiteeService service;

    /**
     * PR converter.
     */
    @Autowired
    private PRConverter converter;

    /**
     * repo service.
     */
    @Autowired
    private RepoService repoService;

    /**
     * PR service.
     */
    @Autowired
    private PRService prService;

    /**
     * 执行 拉取并更新指定组织下仓库信息.
     */
    @Override
    public void run() {
        LOGGER.info("pr data collection starting...... ");
        List<RepoDO> repoDOList = repoService.list();
        for (RepoDO repoDO : repoDOList) {
            getPRList(repoDO);
        }
    }

    /**
     * 获取GitCode平台仓库下PR信息.
     *
     * @param repoDO 仓库信息
     */
    private void getPRList(RepoDO repoDO) {
        int page = 1;
        while (true) {
            if (page > GitCodeConstant.MAX_PAGE) {
                LOGGER.error("pr data collection big page " + ObjectMapperUtil.writeValueAsString(repoDO));
                break;
            }
            String prInfo = null;
            try {
                prInfo = service.getPRInfo(repoDO.getNamespace(), repoDO.getPath(), page);
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
                continue;
            }
            if (GitCodeConstant.NOT_FOUND_RESPONSE.equals(prInfo)
                    || prInfo.startsWith("IOException retry")
                    || GitCodeConstant.NULL_ARRAY_RESPONSE.equals(prInfo)) {
                break;
            }
            page++;
            try {
                ArrayNode arrayNode = ObjectMapperUtil.toObject(ArrayNode.class, prInfo);
                List<PRDO> doList = converter.toDOList(arrayNode, repoDO.getNamespace(), CodePlatformEnum.GITEE);
                if (!doList.isEmpty()) {
                    prService.saveOrUpdateBatch(doList);
                }
            } catch (Exception e) {
                LOGGER.error("pr data collection error >>> " + e.getMessage());
            }
        }
    }
}
