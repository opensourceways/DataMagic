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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.om.DataMagic.common.config.TaskConfig;
import com.om.DataMagic.common.constant.TableConstant;
import com.om.DataMagic.common.util.DateUtil;
import com.om.DataMagic.infrastructure.pgDB.converter.dws.ContributeConverter;
import com.om.DataMagic.infrastructure.pgDB.dataobject.CommentDO;
import com.om.DataMagic.infrastructure.pgDB.dataobject.IssueDO;
import com.om.DataMagic.infrastructure.pgDB.dataobject.PRDO;
import com.om.DataMagic.infrastructure.pgDB.dataobject.dws.ContributeDO;
import com.om.DataMagic.infrastructure.pgDB.dataobject.dws.UserDO;
import com.om.DataMagic.infrastructure.pgDB.service.CommentService;
import com.om.DataMagic.infrastructure.pgDB.service.IssueService;
import com.om.DataMagic.infrastructure.pgDB.service.PRService;
import com.om.DataMagic.infrastructure.pgDB.service.dws.ContributeService;
import com.om.DataMagic.infrastructure.pgDB.service.dws.UserService;
import com.om.DataMagic.process.DriverManager;

@Component
public class GitCodeContributeProcess implements DriverManager {
    /**
     * Autowired field for the UserService.
     */
    @Autowired
    private UserService userService;

    /**
     * Autowired field for the PRService.
     */
    @Autowired
    private PRService prService;

    /**
     * Autowired field for the IssueService.
     */
    @Autowired
    private IssueService issueService;

    /**
     * Autowired field for the CommentService.
     */
    @Autowired
    private CommentService commentService;

    /**
     * Autowired field for the ContributeService.
     */
    @Autowired
    private ContributeService contributeService;

    /**
     * Autowired field for the TaskConfig.
     */
    @Autowired
    private TaskConfig config;

    /**
     * Logger for logging messages in GitCodeProcess class.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(GitCodeContributeProcess.class);

    @Override
    public void run() {
        List<String> robots = Arrays.asList(config.getRobots().split(","));
        composeIssue(robots);
        composePR(robots);
        composeComment();
    }

    /**
     * Compose DWS Issue.
     */
    public void composeIssue(List<String> robots) {
        List<IssueDO> doList = issueService.list();
        Collection<ContributeDO> items = new ArrayList<>();
        for (IssueDO issue : doList) {
            BaseContribute contrib = ContributeConverter.toBaseContribute(issue);
            contrib.setCommentNum(computeCommentNum(contrib.getHtmlUrl(), robots));
            contrib.setFirstReplyTime(computeReplyTime(contrib, robots));
            contrib = setUser(contrib);
            ContributeDO item = ContributeConverter.toEntity(issue, contrib);
            items.add(item);
        }
        contributeService.saveOrUpdateBatch(items);
    }

    /**
     * Compose DWS PR.
     */
    public void composePR(List<String> robots) {
        List<PRDO> doList = prService.list();
        Collection<ContributeDO> items = new ArrayList<>();
        for (PRDO pr : doList) {
            BaseContribute contrib = ContributeConverter.toBaseContribute(pr);
            contrib.setCommentNum(computeCommentNum(contrib.getHtmlUrl(), robots));
            contrib.setFirstReplyTime(computeReplyTime(contrib, robots));
            contrib = setUser(contrib);
            ContributeDO item = ContributeConverter.toEntity(pr, contrib);
            items.add(item);
        }
        contributeService.saveOrUpdateBatch(items);
    }

    /**
     * Compose DWS comment.
     */
    public void composeComment() {
        long commentNum = commentService.count();
        LOGGER.info(String.format("Total comment: %s", commentNum));
        int total = (int) ((commentNum + TableConstant.PAGE_SIZE - 1) / TableConstant.PAGE_SIZE);
        int cur = 1;
        while (cur <= total) {
            IPage<CommentDO> page = new Page<>(cur, TableConstant.PAGE_SIZE);
            IPage<CommentDO> result = commentService.page(page);
            List<CommentDO> doList = result.getRecords();
            Collection<ContributeDO> items = new ArrayList<>();
            for (CommentDO comment : doList) {
                BaseContribute contrib = ContributeConverter.toBaseContribute(comment);
                contrib = setUser(contrib);
                ContributeDO item = ContributeConverter.toEntity(comment, contrib);
                items.add(item);
            }
            cur++;
            contributeService.saveOrUpdateBatch(items);
        }
    }

    /**
     * Compute comment num of a pr or issue.
     *
     * @param htmlUrl The html url for pr or issue
     * @return comment num
     */
    public Integer computeCommentNum(String htmlUrl, List<String> robots) {
        Integer commentNum = 0;
        QueryWrapper<CommentDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tag_url", htmlUrl)
                .notIn("user_login", robots);
        commentNum = (int) commentService.count(queryWrapper);
        return commentNum;
    }

    /**
     * Get first comment of a pr or issue.
     *
     * @param htmlUrl The html url for pr or issue.
     * @return A comment.
     */
    public CommentDO getFirstComment(String htmlUrl, List<String> robots) {
        QueryWrapper<CommentDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tag_url", htmlUrl)
                .eq("self", "false")
                .notIn("user_login", robots)
                .orderByAsc("created_at");

        IPage<CommentDO> page = new Page<>(1, 10);
        List<CommentDO> resultPage = commentService.page(page, queryWrapper).getRecords();
        return resultPage.isEmpty() ? null : resultPage.get(0);
    }

    /**
     * Compute comment num of a pr or issue.
     *
     * @param BaseContribute The BaseContribute object
     * @return The first reply time of a pr or issue
     */
    public Long computeReplyTime(BaseContribute obj, List<String> robots) {
        CommentDO firstComment = getFirstComment(obj.getHtmlUrl(), robots);
        if (null == firstComment) {
            return null;
        }
        Long replyTime = DateUtil.getDuration(obj.getCreatedAt(), firstComment.getCreatedAt());
        return replyTime;
    }

    /**
     * Get user info of a pr or issue..
     *
     * @param BaseContribute The BaseContribute object.
     * @return A UserDO object.
     */
    public UserDO getUser(BaseContribute obj) {
        QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_login", obj.getUserLogin())
                .eq("code_platform", obj.getCodePlatform());

        List<UserDO> results = userService.list(queryWrapper);
        for (UserDO user : results) {
            if (user.getStartDate() == null) {
                user.setStartDate(DateUtil.parse(TableConstant.START_DATE));
            }
            if (user.getEndDate() == null) {
                user.setEndDate(DateUtil.parse(TableConstant.END_DATE));
            }
            if (DateUtil.betweenDuration(obj.getCreatedAt(), user.getStartDate(), user.getEndDate())) {
                return user;
            }
        }
        return results.isEmpty() ? null : results.get(0);
    }

    /**
     * Add user info to a pr or issue..
     *
     * @param BaseContribute The BaseContribute object.
     * @return A BaseContribute object.
     */
    public BaseContribute setUser(BaseContribute obj) {
        UserDO user = getUser(obj);
        if (user == null) {
            return obj;
        }
        obj.setCompany(user.getCompany());
        obj.setCompanyType(user.getCompanyType());
        obj.setInternal(user.getInternal());
        obj.setRole(user.getRole());
        return obj;
    }
}
