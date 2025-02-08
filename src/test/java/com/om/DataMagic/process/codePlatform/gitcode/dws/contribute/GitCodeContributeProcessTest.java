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
package com.om.DataMagic.process.codePlatform.gitcode.dws.contribute;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.JsonNode;
import com.om.DataMagic.common.util.ObjectMapperUtil;
import com.om.DataMagic.infrastructure.pgDB.converter.IssueConverter;
import com.om.DataMagic.infrastructure.pgDB.converter.PRConverter;
import com.om.DataMagic.infrastructure.pgDB.dataobject.CommentDO;
import com.om.DataMagic.infrastructure.pgDB.dataobject.IssueDO;
import com.om.DataMagic.infrastructure.pgDB.dataobject.PRDO;
import com.om.DataMagic.infrastructure.pgDB.service.CommentService;
import com.om.DataMagic.infrastructure.pgDB.service.IssueService;
import com.om.DataMagic.infrastructure.pgDB.service.PRService;
import com.om.DataMagic.infrastructure.pgDB.service.dws.ContributeService;
import com.om.DataMagic.infrastructure.pgDB.service.dws.UserService;

@ExtendWith(MockitoExtension.class)
public class GitCodeContributeProcessTest {
    @Test
    void contextLoads() {
    }

    @Mock
    private UserService userService;

    @Mock
    private PRService prService;

    @Mock
    private IssueService issueService;

    @Mock
    private CommentService commentService;

    @Mock
    private ContributeService contributeService;

    @InjectMocks
    private GitCodeContributeProcess process;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test()
    void testComposePR() throws Exception {
        when(contributeService.saveOrUpdateBatch(anyCollection())).thenReturn(true);
        
        PRConverter prConverter = new PRConverter();
        JsonNode pr = ObjectMapperUtil.toJsonNode(getPRList());
        List<PRDO> prs = new ArrayList<>();
        prs.add(prConverter.toDO(pr));
        when(prService.list()).thenReturn(prs);
        
        List<String> robots = Arrays.asList("opengauss-bot");
        when(commentService.count(any(QueryWrapper.class))).thenReturn(5l);
        IPage<CommentDO> page = new Page<>();
        when(commentService.page(any(IPage.class), any(QueryWrapper.class))).thenReturn(page);
        process.composePR(robots);
    }

    @Test()
    void testComposeIssue() throws Exception {
        when(contributeService.saveOrUpdateBatch(anyCollection())).thenReturn(true);
        
        IssueConverter converter = new IssueConverter();
        JsonNode issue = ObjectMapperUtil.toJsonNode(getIssueList());
        List<IssueDO> issues = new ArrayList<>();
        issues.add(converter.toDO(issue));
        when(issueService.list()).thenReturn(issues);
        
        List<String> robots = Arrays.asList("opengauss-bot");
        when(commentService.count(any(QueryWrapper.class))).thenReturn(5l);
        IPage<CommentDO> page = new Page<>();
        when(commentService.page(any(IPage.class), any(QueryWrapper.class))).thenReturn(page);
        process.composeIssue(robots);
    }

    public String getPRList() {
        return "{\n" +
                "  \"uuid\": \"gitcode-6499048\",\n" +
                "  \"id\": \"6499048\",\n" +
                "  \"number\": \"249\",\n" +
                "  \"url\": \"https://gitcode.com/api/v5/repos/opengaussmirror202502/infra/pulls/249\",\n" +
                "  \"html_url\": \"https://gitcode.com/opengaussmirror202502/infra/merge_requests/249\",\n" +
                "  \"title\": \"close monitor agent\",\n" +
                "  \"body\": \"\",\n" +
                "  \"created_at\": \"2024-06-19T09:47:36+08:00\",\n" +
                "  \"updated_at\": \"2024-06-19T09:47:36+08:00\",\n" +
                "  \"closed_at\": \"2024-06-19T09:47:36+08:00\",\n" +
                "  \"merged_at\": \"2024-06-19T09:47:36+08:00\",\n" +
                "  \"user_login\": \"liuyanglinux\",\n" +
                "  \"state\": \"merged\",\n" +
                "  \"assignees_user_ids\": \"[]\",\n" +
                "  \"labels_id\": \"[\\\"13022923\\\",\\\"13022921\\\",\\\"13021416\\\"]\",\n" +
                "  \"labels_name\": \"[\\\"needs-issue\\\",\\\"opengauss-cla/yes\\\",\\\"sig/infra\\\"]\",\n" +
                "  \"head_label\": \"liuyang02\",\n" +
                "  \"head_ref\": \"liuyang02\",\n" +
                "  \"head_user_id\": \"64e5ed8f7e20aa73efcbc302\",\n" +
                "  \"head_user_login\": \"gitcode-xxm\",\n" +
                "  \"head_repo_id\": \"4579472\",\n" +
                "  \"head_repo_path\": \"infra\",\n" +
                "  \"head_repo_full_name\": \"opengaussmirror202502/infra\",\n" +
                "  \"base_label\": \"master\",\n" +
                "  \"base_ref\": \"master\",\n" +
                "  \"base_user_id\": \"64e5ed8f7e20aa73efcbc302\",\n" +
                "  \"base_user_login\": \"gitcode-xxm\",\n" +
                "  \"base_repo_id\": \"4579472\",\n" +
                "  \"repo_name\": \"infra\",\n" +
                "  \"repo_path\": \"infra\",\n" +
                "  \"base_owner_user_id\": \"64e5ed8f7e20aa73efcbc302\",\n" +
                "  \"base_owner_user_login\": \"gitcode-xxm\",\n" +
                "  \"private\": \"\",\n" +
                "  \"internal\": \"\",\n" +
                "  \"fork\": null,\n" +
                "  \"namespace\": \"opengaussmirror202502\",\n" +
                "  \"code_platform\": \"gitcode\",\n" +
                "  \"is_removed\": null,\n" +
                "  \"added_lines\": null,\n" +
                "  \"removed_lines\": null\n" +
                "}";
    }

    public String getIssueList() {
        return "{\n" +
                "  \"uuid\": \"gitcode-2874800\",\n" +
                "  \"id\": \"2874800\",\n" +
                "  \"number\": \"25\",\n" +
                "  \"url\": \"\",\n" +
                "  \"html_url\": \"https://gitcode.com/opengaussmirror202502/infra/issues/25\",\n" +
                "  \"title\": \"openGauss 官网搜索引入人工智能搜索结果\",\n" +
                "  \"body\": \"用户进入搜索框进行文档资料搜索时****************\",\n" +
                "  \"created_at\": \"2024-06-19T09:47:36+08:00\",\n" +
                "  \"updated_at\": \"2024-06-19T09:47:36+08:00\",\n" +
                "  \"closed_at\": null,\n" +
                "  \"deadline\": null,\n" +
                "  \"user_login\": \"liuyanglinux\",\n" +
                "  \"state\": \"open\",\n" +
                "  \"plan_started_at\": null,\n" +
                "  \"labels_id\": \"[\\\"13022922\\\"]\",\n" +
                "  \"labels_name\": \"[\\\"sig/infra\\\"]\",\n" +
                "  \"assignees_user_ids\": \"\",\n" +
                "  \"issue_state\": \"待办的\",\n" +
                "  \"issue_type\": \"任务\",\n" +
                "  \"priority\": \"0\",\n" +
                "  \"program\": null,\n" +
                "  \"repo_id\": \"4579472\",\n" +
                "  \"repo_name\": \"infra\",\n" +
                "  \"repo_path\": \"infra\",\n" +
                "  \"security_hole\": \"\",\n" +
                "  \"namespace\": \"opengaussmirror202502\",\n" +
                "  \"code_platform\": \"gitcode\"\n" +
                "}";
    }
}
