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

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.om.DataMagic.common.util.ObjectMapperUtil;
import com.om.DataMagic.domain.codePlatform.gitcode.primitive.GitEnum;
import com.om.DataMagic.infrastructure.pgDB.dataobject.CommentDO;
import com.om.DataMagic.infrastructure.pgDB.dataobject.IssueDO;
import com.om.DataMagic.infrastructure.pgDB.dataobject.PRDO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

/**
 * CommentConverter 测试类
 *
 * @author zhaoyan
 * @since 2025-01-17
 */
@ExtendWith(MockitoExtension.class)
public class CommentConverterTest {

    @Test
    @DisplayName("json数组转化为DO数组 PR")
    void testTODOSuccessByPR() {
        PRDO prdo = new PRDO();
        prdo.setHtmlUrl("prHtmlUrl");
        prdo.setUserId("708");

        CommentConverter converter = new CommentConverter();

        ArrayNode arrayNode = ObjectMapperUtil.toObject(ArrayNode.class, getCommentByPRFromAPI());
        Assertions.assertNotNull(arrayNode);

        List<CommentDO> doList = converter.toDOList(arrayNode, prdo);
        Assertions.assertEquals(1, doList.size());
        Assertions.assertEquals(GitEnum.COMMENT_PR.getValue(), doList.get(0).getCommentType());
        Assertions.assertEquals("prHtmlUrl", doList.get(0).getTagUrl());
        Assertions.assertEquals("true", doList.get(0).getIsSelf());
    }

    @Test
    @DisplayName("json数组转化为DO数组 Issue")
    void testTODOSuccessByIssue() {
        IssueDO issueDO = new IssueDO();
        issueDO.setHtmlUrl("issueHtmlUrl");
        issueDO.setUserId("661ce4eab470b1430d456154");

        CommentConverter converter = new CommentConverter();

        ArrayNode arrayNode = ObjectMapperUtil.toObject(ArrayNode.class, getCommentByIssueFromAPI());
        Assertions.assertNotNull(arrayNode);

        List<CommentDO> doList = converter.toDOList(arrayNode, issueDO);
        Assertions.assertEquals(1, doList.size());
        Assertions.assertEquals(GitEnum.COMMENT_ISSUE.getValue(), doList.get(0).getCommentType());
        Assertions.assertEquals("issueHtmlUrl", doList.get(0).getTagUrl());
        Assertions.assertEquals("true", doList.get(0).getIsSelf());
    }

    public String getCommentByPRFromAPI(){
        return "[\n" +
                "  {\n" +
                "    \"id\": \"de772738e6dab92174c0e86c052ccf9bed24f747\",\n" +
                "    \"body\": \"111\",\n" +
                "    \"created_at\": \"2024-04-19T07:48:59.755+00:00\",\n" +
                "    \"updated_at\": \"2024-04-19T07:48:59.755+00:00\",\n" +
                "    \"user\": {\n" +
                "      \"id\": 708,\n" +
                "      \"login\": \"Lzm_0916\",\n" +
                "      \"name\": \"Lzm_0916\",\n" +
                "      \"avatar_url\": \"https://gitcode-img.obs.cn-south-1.myhuaweicloud.com:443/cb/da/6cb18d9ae9f1a94b4f640d3b848351c352c7869f33d0cb68e7acad4f224c4e23.png\",\n" +
                "      \"html_url\": \"https://test.gitcode.net/Lzm_0916\"\n" +
                "    }\n" +
                "  }\n" +
                "]\n";
    }

    public String getCommentByIssueFromAPI(){
        return "[\n" +
                "  {\n" +
                "    \"id\": 271624,\n" +
                "    \"body\": \"评论内容。\",\n" +
                "    \"user\": {\n" +
                "      \"avatar_url\": \"https://gitcode-img.obs.cn-south-1.myhuaweicloud.com:443/fa/fe/f32a9fecc53e890afbd48fd098b0f6c5f20f062581400c76c85e5baab3f0d5b2.png\",\n" +
                "      \"events_url\": null,\n" +
                "      \"followers_url\": null,\n" +
                "      \"following_url\": null,\n" +
                "      \"gists_url\": null,\n" +
                "      \"html_url\": \"https://test.gitcode.net/dengmengmian\",\n" +
                "      \"id\": \"661ce4eab470b1430d456154\",\n" +
                "      \"login\": \"dengmengmian\",\n" +
                "      \"member_role\": null,\n" +
                "      \"name\": \"麻凡_\",\n" +
                "      \"organizations_url\": null,\n" +
                "      \"received_events_url\": null,\n" +
                "      \"remark\": null,\n" +
                "      \"repos_url\": null,\n" +
                "      \"starred_url\": null,\n" +
                "      \"subscriptions_url\": null,\n" +
                "      \"type\": null,\n" +
                "      \"url\": null\n" +
                "    },\n" +
                "    \"target\": {\n" +
                "      \"issue\": {\n" +
                "        \"id\": 152134,\n" +
                "        \"title\": \"\",\n" +
                "        \"nubmer\": 1\n" +
                "      }\n" +
                "    },\n" +
                "    \"created_at\": \"2024-04-19T17:50:18.199+08:00\",\n" +
                "    \"updated_at\": \"2024-04-19T17:50:18.199+08:00\"\n" +
                "  }\n" +
                "]\n";
    }
}
