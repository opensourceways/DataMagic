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

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.om.DataMagic.client.codePlatform.gitcode.GitCodeClient;
import com.om.DataMagic.common.util.ObjectMapperUtil;
import com.om.DataMagic.domain.codePlatform.gitcode.primitive.GitCodeConstant;
import com.om.DataMagic.infrastructure.pgDB.converter.CommentConverter;
import com.om.DataMagic.infrastructure.pgDB.dataobject.CommentDO;
import com.om.DataMagic.infrastructure.pgDB.dataobject.PRDO;
import com.om.DataMagic.infrastructure.pgDB.dataobject.RepoDO;
import com.om.DataMagic.infrastructure.pgDB.service.*;
import com.om.DataMagic.process.DriverManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * comment application service
 *
 * @author zhaoyan
 * @since 2025-01-17
 */
@Component
public class GitCodeCommentProcess implements DriverManager {

    @Autowired
    GitCodeClient client;

    @Autowired
    CommentConverter converter;

    @Autowired
    PRService prService;

    @Autowired
    RepoService repoService;

    @Autowired
    CommentService commentService;

    /**
     * 执行 拉取并更新指定组织下仓库下Comment信息
     */
    @Override
    public void run() {
        List<CommentDO> commentDOList = new ArrayList<>();

        List<RepoDO> repoDOList = repoService.list();
        for (RepoDO repoDO : repoDOList) {
            commentDOList.addAll(getCommentListByIssue(repoDO));
        }

        List<PRDO> prdoList = prService.list();
        for (PRDO prdo : prdoList) {
            commentDOList.addAll(getCommentListByPr(prdo));
        }

        commentService.saveOrUpdateBatch(commentDOList);
    }


    /**
     * 获取GitCode平台仓库下PR下评论信息
     *
     * @param prdo PR信息
     * @return PR信息字符串
     */
    private List<CommentDO> getCommentListByPr(PRDO prdo) {
        List<String> commentArrayList = new ArrayList<>();
        int page = 1;
        while (true) {
            String commentInfo = client.getCommentInfoByPR(prdo.getNamespace(), prdo.getRepoName(), prdo.getNumber(), page);
            if (GitCodeConstant.NULL_ARRAY_RESPONSE.equals(commentInfo)) {
                break;
            }
            page++;
            commentArrayList.add(commentInfo);
        }
        return formatStrByPr(prdo, commentArrayList);
    }

    /**
     * 转化并组装CommentDO数据
     *
     * @param prdo             PR信息
     * @param commentArrayList comment信息字符串
     * @return comment do 对象
     */
    private List<CommentDO> formatStrByPr(PRDO prdo, List<String> commentArrayList) {
        List<ArrayNode> arrayNodeList = commentArrayList.stream().map(
                prArray -> ObjectMapperUtil.toObject(ArrayNode.class, prArray)).toList();
        List<CommentDO> commentDOList = new ArrayList<>();
        for (ArrayNode arrayNode : arrayNodeList) {
            commentDOList.addAll(converter.toDOList(arrayNode, prdo));
        }
        return commentDOList;
    }


    /**
     * 获取GitCode平台仓库下ISSUE评论信息
     *
     * @param repoDO 仓库信息
     * @return PR信息字符串
     */
    private List<CommentDO> getCommentListByIssue(RepoDO repoDO) {
        List<String> commentArrayList = new ArrayList<>();
        int page = 1;
        while (true) {
            String commentInfo = client.getCommentInfoByIssue(repoDO.getOwnerName(), repoDO.getRepoName(), page);
            if (GitCodeConstant.NULL_ARRAY_RESPONSE.equals(commentInfo)) {
                break;
            }
            page++;
            commentArrayList.add(commentInfo);
        }
        return formatStrByIssue(repoDO, commentArrayList);
    }

    /**
     * 转化并组装CommentDO数据
     *
     * @param repoDO           仓库信息
     * @param commentArrayList comment信息字符串
     * @return comment do 对象
     */
    private List<CommentDO> formatStrByIssue(RepoDO repoDO, List<String> commentArrayList) {
        List<ArrayNode> arrayNodeList = commentArrayList.stream().map(
                prArray -> ObjectMapperUtil.toObject(ArrayNode.class, prArray)).toList();
        List<CommentDO> commentDOList = new ArrayList<>();
        for (ArrayNode arrayNode : arrayNodeList) {
            commentDOList.addAll(converter.toDOList(arrayNode, repoDO));
        }
        return commentDOList;
    }
}