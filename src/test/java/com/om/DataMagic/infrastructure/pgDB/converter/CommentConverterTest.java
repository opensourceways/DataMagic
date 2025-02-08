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
import com.om.DataMagic.common.constant.CodePlatformEnum;
import com.om.DataMagic.common.constant.GitEnum;
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
        prdo.setCodePlatform(CodePlatformEnum.GITCODE.getText());
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
        Assertions.assertEquals("prHtmlUrl#tid-de772738e6dab92174c0e86c052ccf9bed24f747", doList.get(0).getHtmlUrl());
    }

    @Test
    @DisplayName("json数组转化为DO数组 Issue")
    void testTODOSuccessByIssue() {
        IssueDO issueDO = new IssueDO();
        issueDO.setCodePlatform(CodePlatformEnum.GITCODE.getText());
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
        Assertions.assertEquals("issueHtmlUrl#tid-271624", doList.get(0).getHtmlUrl());
    }

    @Test
    @DisplayName("json数组转化为DO数组 Gitee PR")
    void testTODOSuccessByGiteePR() {
        PRDO prdo = new PRDO();
        prdo.setCodePlatform(CodePlatformEnum.GITEE.getText());
        prdo.setHtmlUrl("prHtmlUrl");
        prdo.setUserId("13000000");

        CommentConverter converter = new CommentConverter();

        ArrayNode arrayNode = ObjectMapperUtil.toObject(ArrayNode.class, getCommentByPrFromGitee());
        Assertions.assertNotNull(arrayNode);

        List<CommentDO> doList = converter.toDOList(arrayNode, prdo);
        Assertions.assertEquals(1, doList.size());
        Assertions.assertEquals(GitEnum.COMMENT_PR.getValue(), doList.get(0).getCommentType());
        Assertions.assertEquals("prHtmlUrl", doList.get(0).getTagUrl());
        Assertions.assertEquals("true", doList.get(0).getIsSelf());
        Assertions.assertEquals("opengauss/infra/pulls/253#note_33548559_conversation_130632055", doList.get(0).getHtmlUrl());
    }

    @Test
    @DisplayName("json数组转化为DO数组 Issue")
    void testTODOSuccessByGiteeIssue() {
        IssueDO issueDO = new IssueDO();
        issueDO.setCodePlatform(CodePlatformEnum.GITEE.getText());
        issueDO.setHtmlUrl("issueHtmlUrl");
        issueDO.setUserId("13000000");

        CommentConverter converter = new CommentConverter();

        ArrayNode arrayNode = ObjectMapperUtil.toObject(ArrayNode.class, getCommentByIssueFromGitee());
        Assertions.assertNotNull(arrayNode);

        List<CommentDO> doList = converter.toDOList(arrayNode, issueDO);
        Assertions.assertEquals(1, doList.size());
        Assertions.assertEquals(GitEnum.COMMENT_ISSUE.getValue(), doList.get(0).getCommentType());
        Assertions.assertEquals("issueHtmlUrl", doList.get(0).getTagUrl());
        Assertions.assertEquals("true", doList.get(0).getIsSelf());
        Assertions.assertEquals("issueHtmlUrl#note_24302778_link", doList.get(0).getHtmlUrl());
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
    
    public String getCommentByPrFromGitee(){
        return "[\n" +
                "    {\n" +
                "        \"url\": \"api/v5/repos/opengauss/infra/pulls/comments/33548559\",\n" +
                "        \"id\": 33548559,\n" +
                "        \"path\": null,\n" +
                "        \"position\": null,\n" +
                "        \"original_position\": null,\n" +
                "        \"new_line\": null,\n" +
                "        \"commit_id\": null,\n" +
                "        \"original_commit_id\": null,\n" +
                "        \"user\": {\n" +
                "            \"id\": 13000000,\n" +
                "            \"login\": \"***\",\n" +
                "            \"name\": \"***\",\n" +
                "            \"type\": \"User\"\n" +
                "        },\n" +
                "        \"created_at\": \"2024-10-31T14:27:44+08:00\",\n" +
                "        \"updated_at\": \"2024-10-31T14:27:44+08:00\",\n" +
                "        \"body\": \"@**** , PullRequest must be associated with at least one issue.\",\n" +
                "        \"html_url\": \"opengauss/infra/pulls/253#note_33548559\",\n" +
                "        \"pull_request_url\": \"api/v5/repos/opengauss/infra/pulls/253\",\n" +
                "        \"_links\": {\n" +
                "            \"self\": {\n" +
                "                \"href\": \"api/v5/repos/opengauss/infra/pulls/comments/33548559\"\n" +
                "            },\n" +
                "            \"html\": {\n" +
                "                \"href\": \"opengauss/infra/pulls/253#note_33548559_conversation_130632055\"\n" +
                "            },\n" +
                "            \"pull_request\": {\n" +
                "                \"href\": \"api/v5/repos/opengauss/infra/pulls/253\"\n" +
                "            }\n" +
                "        },\n" +
                "        \"comment_type\": \"pr_comment\"\n" +
                "    }\n" +
                "]";
    }

    public  String getCommentByIssueFromGitee(){
        return "[\n" +
                "    {\n" +
                "        \"id\": 24302778,\n" +
                "        \"body\": \"Hey ***@mystarry-sky***, Welcome to openGauss Community.\\nAll of the projects in openGauss Community are maintained by ***@opengauss_bot***.\\nThat means the developers can comment below every pull request or issue to trigger Bot Commands.\\nPlease follow instructions at **[Here](opengauss/community/blob/master/contributors/command.en.md)** to find the details.\\n\",\n" +
                "        \"user\": {\n" +
                "            \"id\": 13000000,\n" +
                "            \"login\": \"*****\",\n" +
                "            \"name\": \"*****\",\n" +
                "            \"type\": \"User\"\n" +
                "        },\n" +
                "        \"source\": null,\n" +
                "        \"target\": {\n" +
                "            \"issue\": {\n" +
                "                \"id\": 14895024,\n" +
                "                \"title\": \"openGauss 官网搜索引入人工智能搜索结果\",\n" +
                "                \"number\": \"I8V92O\"\n" +
                "            },\n" +
                "            \"pull_request\": null\n" +
                "        },\n" +
                "        \"created_at\": \"2024-01-11T10:22:09+08:00\",\n" +
                "        \"updated_at\": \"2024-01-11T10:22:09+08:00\"\n" +
                "    }\n" +
                "]";
    }
}
