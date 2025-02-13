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
import com.om.DataMagic.common.constant.GitCodeConstant;
import com.om.DataMagic.infrastructure.pgDB.converter.CommentConverter;
import com.om.DataMagic.infrastructure.pgDB.dataobject.CommentDO;
import com.om.DataMagic.infrastructure.pgDB.dataobject.IssueDO;
import com.om.DataMagic.infrastructure.pgDB.dataobject.PRDO;
import com.om.DataMagic.infrastructure.pgDB.service.platform.CommentService;
import com.om.DataMagic.infrastructure.pgDB.service.platform.IssueService;
import com.om.DataMagic.infrastructure.pgDB.service.platform.PRService;
import com.om.DataMagic.process.DriverManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * comment application service.
 *
 * @author zhaoyan
 * @since 2025-02-06
 */
@Component
public class GiteeCommentProcess implements DriverManager {
    /**
     * Logger for logging messages in App class.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(GiteeCommentProcess.class);
    /**
     * gitee service .
     */
    @Autowired
    private GiteeService service;

    /**
     * comment converter .
     */
    @Autowired
    private CommentConverter converter;

    /**
     * pr service.
     */
    @Autowired
    private PRService prService;

    /**
     * issue service .
     */
    @Autowired
    private IssueService issueService;

    /**
     * comment service .
     */
    @Autowired
    private CommentService commentService;

    /**
     * 执行 拉取并更新指定组织下仓库下Comment信息.
     */
    @Override
    public void run() {
        LOGGER.info("comment data collection starting...... ");
        List<PRDO> prdoList = prService.list();
        for (PRDO prdo : prdoList) {
            getCommentListByPr(prdo);
        }

        List<IssueDO> issueDOList = issueService.list();
        for (IssueDO issueDO : issueDOList) {
            getCommentListByIssue(issueDO);
        }
    }


    /**
     * 获取GitCode平台仓库下PR下评论信息.
     *
     * @param prdo PR信息
     */
    private void getCommentListByPr(PRDO prdo) {
        int page = 1;
        while (true) {
            if (page > GitCodeConstant.MAX_PAGE) {
                LOGGER.error("pr comment data collection big page " + ObjectMapperUtil.writeValueAsString(prdo));
                break;
            }
            String commentInfo = null;
            try {
                commentInfo = service.getCommentInfoByPR(prdo.getNamespace(),
                        prdo.getRepoPath(), prdo.getNumber(), page);
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
                continue;
            }
            if (GitCodeConstant.NOT_FOUND_RESPONSE.equals(commentInfo)
                    || commentInfo.startsWith("IOException retry")
                    || GitCodeConstant.NULL_ARRAY_RESPONSE.equals(commentInfo)) {
                break;
            }
            page++;
            try {
                ArrayNode arrayNode = ObjectMapperUtil.toObject(ArrayNode.class, commentInfo);
                List<CommentDO> doList = converter.toDOList(arrayNode, prdo);
                if (!doList.isEmpty()) {
                    commentService.saveOrUpdateBatch(doList);
                }
            } catch (Exception e) {
                LOGGER.error("pr comment data collection error >>> " + e.getMessage());
            }
        }
    }

    /**
     * 获取GitCode平台仓库下ISSUE评论信息.
     * 此接口存在bug，接口调用任意page值均有返回，会导致无法停止循环
     *
     * @param issueDO issue信息
     */
    private void getCommentListByIssue(IssueDO issueDO) {
        int page = 1;
        while (true) {
            if (page > GitCodeConstant.MAX_PAGE) {
                LOGGER.error("issue comment data collection big page " + ObjectMapperUtil.writeValueAsString(issueDO));
                break;
            }
            String commentInfo = null;
            try {
                commentInfo = service.getCommentInfoByIssue(issueDO.getNamespace(),
                        issueDO.getRepoPath(), issueDO.getNumber(), page);
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
                continue;
            }
            if (GitCodeConstant.NOT_FOUND_RESPONSE.equals(commentInfo)
                    || commentInfo.startsWith("IOException retry")
                    || GitCodeConstant.NULL_ARRAY_RESPONSE.equals(commentInfo)) {
                break;
            }
            page++;
            try {
                ArrayNode arrayNode = ObjectMapperUtil.toObject(ArrayNode.class, commentInfo);
                List<CommentDO> doList = converter.toDOList(arrayNode, issueDO);
                if (!doList.isEmpty()) {
                    commentService.saveOrUpdateBatch(doList);
                }
            } catch (Exception e) {
                LOGGER.error("issue comment data collection error >>> " + e.getMessage());
            }
        }
    }
}
