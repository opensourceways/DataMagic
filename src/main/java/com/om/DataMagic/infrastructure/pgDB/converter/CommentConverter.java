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

package com.om.DataMagic.infrastructure.pgDB.converter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.om.DataMagic.common.util.DateUtil;
import com.om.DataMagic.domain.codePlatform.gitcode.primitive.CodePlatformEnum;
import com.om.DataMagic.domain.codePlatform.gitcode.primitive.GitEnum;
import com.om.DataMagic.infrastructure.pgDB.dataobject.CommentDO;
import com.om.DataMagic.infrastructure.pgDB.dataobject.IssueDO;
import com.om.DataMagic.infrastructure.pgDB.dataobject.PRDO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * comment 转换器
 *
 * @author zhaoyan
 * @since 2025-01-17
 */
@Component
public class CommentConverter {

    /**
     * 将Comment json数组转化为DO list
     *
     * @param arrayNode json数组
     * @param prdo      pr对象
     * @return DO list
     */
    public List<CommentDO> toDOList(ArrayNode arrayNode, PRDO prdo) {
        List<CommentDO> issueDOList = new ArrayList<>();
        for (JsonNode issueNode : arrayNode) {
            CommentDO commentDO = toDO(issueNode);
            commentDO.setCommentType(GitEnum.COMMENT_PR.getValue());
            commentDO.setTagUrl(prdo.getHtmlUrl());
            commentDO.setIsSelf(String.valueOf(prdo.getUserId().equals(commentDO.getUserId())));
            issueDOList.add(commentDO);
        }
        return issueDOList;
    }

    /**
     * 将Comment json数组转化为DO list
     *
     * @param arrayNode json数组
     * @param issueDO   issue对象
     * @return DO list
     */
    public List<CommentDO> toDOList(ArrayNode arrayNode, IssueDO issueDO) {
        List<CommentDO> issueDOList = new ArrayList<>();
        for (JsonNode issueNode : arrayNode) {
            CommentDO commentDO = toDO(issueNode);
            commentDO.setCommentType(GitEnum.COMMENT_ISSUE.getValue());
            commentDO.setTagUrl(issueDO.getHtmlUrl());
            commentDO.setIsSelf(String.valueOf(issueDO.getUserId().equals(commentDO.getUserId())));
            issueDOList.add(commentDO);
        }
        return issueDOList;
    }

    public CommentDO toDO(JsonNode commentJson) {
        CommentDO commentDO = new CommentDO();
        commentDO.setId(commentJson.path("id").asText());
        commentDO.setCodePlatform(CodePlatformEnum.GITCODE.getText());
        commentDO.setUuid(CodePlatformEnum.GITCODE.getText() + "-" + commentDO.getId());
        commentDO.setHtmlUrl(null);
        commentDO.setBody(commentJson.path("body").asText());
        commentDO.setCreatedAt(DateUtil.parse(commentJson.path("created_at").asText()));
        commentDO.setUpdatedAt(DateUtil.parse(commentJson.path("updated_at").asText()));
        commentDO.setUserId(commentJson.path("user").path("id").asText());
        commentDO.setUserLogin(commentJson.path("user").path("login").asText());
        return commentDO;
    }
}
