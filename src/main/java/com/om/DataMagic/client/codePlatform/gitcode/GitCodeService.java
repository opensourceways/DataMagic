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

package com.om.DataMagic.client.codePlatform.gitcode;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.om.DataMagic.common.exception.RateLimitException;
import com.om.DataMagic.common.util.ObjectMapperUtil;
import com.om.DataMagic.common.constant.GitCodeConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * gitcode 平台service.
 *
 * @author zhaoyan
 * @since 2025-01-24
 */
@Service
public class GitCodeService {

    /**
     * gitcode client.
     */
    @Autowired
    private GitCodeClient client;

    /**
     * Logger for logging messages in App class.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(GitCodeService.class);

    /**
     * @param username .
     * @return userinfo
     */
    public String getUserInfo(String username) {
        String path = "/users/" + username;
        Map<String, String> params = Map.of(
                "page", "1",
                "pageSize", "100");
        try {
            return client.callApi(path, params);
        } catch (RateLimitException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 分页获取某个组织下所有仓库数据.
     *
     * @param orgName 组织名称
     * @param page    当前页
     * @return 仓库数据字符串
     */
    public String getRepoInfo(String orgName, int page) {
        String path = String.format("/orgs/%s/repos", orgName);
        Map<String, String> params = new HashMap<>();
        params.put("page", String.valueOf(page));
        params.put("per_page", String.valueOf(GitCodeConstant.MAX_PER_PAGE));
        try {
            return client.callApi(path, params);
        } catch (RateLimitException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 分页获取仓库所有者下的某个仓库的pr数据.
     *
     * @param ownerName 仓库所有者
     * @param repoName  仓库名称
     * @param page      当前页
     * @return pr数据字符串
     */
    public String getPRInfo(String ownerName, String repoName, int page) {
        String path = String.format("/repos/%s/%s/pulls", ownerName, repoName);
        Map<String, String> params = new HashMap<>();
        params.put("page", String.valueOf(page));
        params.put("per_page", String.valueOf(GitCodeConstant.MAX_PER_PAGE));
        try {
            return client.callApi(path, params);
        } catch (RateLimitException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 分页获取仓库所有者下的某个仓库的pr下所有评论.
     *
     * @param ownerName 仓库所有者
     * @param repoName  仓库名称
     * @param number    pr 序号
     * @param page      当前页
     * @return comment数据字符串
     */
    public String getCommentInfoByPR(String ownerName, String repoName, String number, int page) {
        String path = String.format("/repos/%s/%s/pulls/%s/comments", ownerName, repoName, number);
        Map<String, String> params = new HashMap<>();
        params.put("page", String.valueOf(page));
        params.put("per_page", String.valueOf(GitCodeConstant.MAX_PER_PAGE));
        try {
            return client.callApi(path, params);
        } catch (RateLimitException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 分页获取仓库所有者下的某个仓库的issue数据.
     *
     * @param ownerName 仓库所有者
     * @param repoName  仓库名称
     * @param page      当前页
     * @return issue数据字符串
     */
    public String getIssueInfo(String ownerName, String repoName, int page) {
        String path = String.format("/repos/%s/%s/issues", ownerName, repoName);
        Map<String, String> params = new HashMap<>();
        params.put("page", String.valueOf(page));
        params.put("per_page", String.valueOf(GitCodeConstant.MAX_PER_PAGE));
        try {
            return client.callApi(path, params);
        } catch (RateLimitException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 分页获取仓库所有者下的某个仓库的issue评论数据.
     *
     * @param ownerName 仓库所有者
     * @param repoName  仓库名称
     * @param number    issue 序号
     * @param page      当前页
     * @return comment数据字符串
     */
    public String getCommentInfoByIssue(String ownerName, String repoName, String number, int page) {
        String path = String.format("/repos/%s/%s/issues/%s/comments", ownerName, repoName, number);
        Map<String, String> params = new HashMap<>();
        params.put("page", String.valueOf(page));
        params.put("per_page", String.valueOf(GitCodeConstant.MAX_PER_PAGE));
        try {
            return client.callApi(path, params);
        } catch (RateLimitException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 分页获取仓库所有者下的某个仓库的star数据.
     *
     * @param ownerName 仓库所有者
     * @param repoName  仓库名称
     * @return star数据字符串
     */
    public List<ArrayNode> getStarInfo(String ownerName, String repoName) {
        return geGitCodeArrayNodeByApi("/repos/%s/%s/stargazers", ownerName, repoName);
    }

    /**
     * 分页获取仓库所有者下的某个仓库的watch数据.
     *
     * @param ownerName 仓库所有者
     * @param repoName  仓库名称
     * @return issue数据字符串
     */
    public List<ArrayNode> getWatchInfo(String ownerName, String repoName) {
        return geGitCodeArrayNodeByApi("/repos/%s/%s/subscribers", ownerName, repoName);
    }

    /**
     * 分页获取仓库所有者下的某个仓库的fork数据.
     *
     * @param ownerName 仓库所有者
     * @param repoName  仓库名称
     * @return fork数据字符串
     */
    public List<ArrayNode> getForkInfo(String ownerName, String repoName) {
        return geGitCodeArrayNodeByApi("/repos/%s/%s/forks", ownerName, repoName);
    }

    /**
     * 分页获取仓库所有者下的某个仓库的分页接口的数据.
     *
     * @param ownerName 仓库所有者
     * @param repoName  仓库名称
     * @param api  仓库名称
     * @return 接口返回的json数据字符串
     */
    public List<ArrayNode> geGitCodeArrayNodeByApi(String api, String ownerName, String repoName) {
        String path = String.format(api, ownerName, repoName);
        Map<String, String> params = new HashMap<>();
        int page = 1;
        params.put("per_page", String.valueOf(GitCodeConstant.MAX_PER_PAGE));
        List<ArrayNode> list = new ArrayList<>();
        while (true) {
            params.put("page", String.valueOf(page));
            String jsonInfo;
            try {
                jsonInfo = client.callApi(path, params);
                ArrayNode object = ObjectMapperUtil.toObject(ArrayNode.class, jsonInfo);
                list.add(object);
                if (object.size() < GitCodeConstant.MAX_PER_PAGE) {
                    break;
                }
            } catch (Exception e) {
                LOGGER.error("api:{},errorInfo:{}", e.getMessage());
                LOGGER.error("接口获取数据失败");
                break;
            }
            page++;
        }
        return list;
    }
}
