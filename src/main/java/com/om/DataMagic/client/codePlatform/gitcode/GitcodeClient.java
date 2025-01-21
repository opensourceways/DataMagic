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

import com.om.DataMagic.common.config.PlatformAccessConfig;
import com.om.DataMagic.common.config.PlatformBaseApiConfig;
import com.om.DataMagic.common.config.TaskConfig;
import com.om.DataMagic.common.util.HttpClientUtil;
import com.om.DataMagic.domain.codePlatform.gitcode.primitive.GitCodeConstant;
import org.apache.http.Header;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class GitcodeClient {

    @Autowired
    TaskConfig config;

    @Autowired
    HttpClientUtil client;

    @Autowired
    PlatformAccessConfig accessConfig;

    @Autowired
    PlatformBaseApiConfig baseApiConfig;

    /**
     * Logger for logging messages in App class.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(GitcodeClient.class);

    /**
     * Send a POST request using an HTTP client to the specified URI with the given
     * request body.
     *
     * @param path   The path for the api request.
     * @param params The params for the api request.
     * @return The response from the request as a string.
     */
    public String callApi(String path, Map<String, String> params) {
        String url = "";
        try {
            URIBuilder uriBuilder = new URIBuilder(config.getBaseApi() + path);
            if (params != null && !params.isEmpty()) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    uriBuilder.addParameter(entry.getKey(), entry.getValue());
                }
            }
            url = uriBuilder.build().toString();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        Header header = new BasicHeader("Authorization", "Bearer " + config.getToken());

        String response = "";
        try {
            response = client.getHttpClient(url, header);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        return response;
    }


    public String callApiByPlatform(String path, Map<String, String> params) {
        String url = "";
        try {
            URIBuilder uriBuilder = new URIBuilder(config.getBaseApi() + path);
            if (params != null && !params.isEmpty()) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    uriBuilder.addParameter(entry.getKey(), entry.getValue());
                }
            }
            url = uriBuilder.build().toString();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        Header header = new BasicHeader("Authorization", "Bearer " + config.getToken());

        String response = "";
        try {
            response = client.getHttpClient(url, header);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        return response;
    }


    public String getUserInfo(String username) {
        String path = "/users/" + username;
        Map<String, String> params = Map.of(
                "page", "1",
                "pageSize", "100");
        return callApi(path, params);
    }

    /**
     * 分页获取某个组织下所有仓库数据
     * @param orgName 组织名称
     * @param page 当前页
     * @return 仓库数据字符串
     */
    public String getRepoInfo(String orgName, int page) {
        String path = String.format("/orgs/%s/repos", orgName);
        Map<String,String> params = new HashMap<>();
        params.put("page", String.valueOf(page));
        params.put("per_page", String.valueOf(GitCodeConstant.MAX_PER_PAGE));
        return callApi(path,params);
    }

    /**
     * 分页获取仓库所有者下的某个仓库的pr数据
     * @param ownerName 仓库所有者
     * @param repoName 仓库名称
     * @param page 当前页
     * @return pr数据字符串
     */
    public String getPRInfo(String ownerName, String repoName, int page) {
        String path = String.format("/repos/%s/%s/pulls", ownerName, repoName);
        Map<String,String> params = new HashMap<>();
        params.put("page", String.valueOf(page));
        params.put("per_page", String.valueOf(GitCodeConstant.MAX_PER_PAGE));
        return callApi(path,params);
    }

    /**
     * 分页获取仓库所有者下的某个仓库的issue数据
     * @param ownerName 仓库所有者
     * @param repoName 仓库名称
     * @param page 当前页
     * @return issue数据字符串
     */
    public String getIssueInfo(String ownerName, String repoName, int page) {
        String path = String.format("/repos/%s/%s/issues", ownerName, repoName);
        Map<String,String> params = new HashMap<>();
        params.put("page", String.valueOf(page));
        params.put("per_page", String.valueOf(GitCodeConstant.MAX_PER_PAGE));
        return callApi(path,params);
    }


    /**
     * 分页获取仓库所有者下的某个仓库的star数据
     * @param ownerName 仓库所有者
     * @param repoName 仓库名称
     * @param page 当前页
     * @return issue数据字符串
     * GET https://api.gitcode.com/api/v5/repos/{owner}/{repo}/stargazers
     */
    public String getStarInfo(String ownerName, String repoName, int page) {
        String path = String.format("/repos/%s/%s/stargazers", ownerName, repoName);
        Map<String,String> params = new HashMap<>();
        params.put("page", String.valueOf(page));
        params.put("per_page", String.valueOf(GitCodeConstant.MAX_PER_PAGE));
        return callApiByPlatform(path,params);
    }


    /**
     * 分页获取仓库所有者下的某个仓库的watch数据
     * @param ownerName 仓库所有者
     * @param repoName 仓库名称
     * @param page 当前页
     * @return issue数据字符串
     * GET https://api.gitcode.com/api/v5/repos/{owner}/{repo}/subscribers
     */
    public String getWatchInfo(String ownerName, String repoName, int page) {
        String path = String.format("/repos/%s/%s/subscribers", ownerName, repoName);
        Map<String,String> params = new HashMap<>();
        params.put("page", String.valueOf(page));
        params.put("per_page", String.valueOf(GitCodeConstant.MAX_PER_PAGE));
        return callApiByPlatform(path,params);
    }

    /**
     * 分页获取仓库所有者下的某个仓库的fork数据
     * @param ownerName 仓库所有者
     * @param repoName 仓库名称
     * @param page 当前页
     * @return issue数据字符串
     * GET https://api.gitcode.com/api/v5/repos/{owner}/{repo}/forks
     */
    public String getForkInfo(String ownerName, String repoName, int page) {
        String path = String.format("/repos/%s/%s/forks", ownerName, repoName);
        Map<String,String> params = new HashMap<>();
        params.put("page", String.valueOf(page));
        params.put("per_page", String.valueOf(GitCodeConstant.MAX_PER_PAGE));
        return callApiByPlatform(path,params);
    }



}
