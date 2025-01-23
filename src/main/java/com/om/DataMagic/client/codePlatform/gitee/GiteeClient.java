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

package com.om.DataMagic.client.codePlatform.gitee;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.om.DataMagic.client.codePlatform.gitcode.GitCodeClient;
import com.om.DataMagic.common.config.PlatformAccessConfig;
import com.om.DataMagic.common.config.PlatformBaseApiConfig;
import com.om.DataMagic.common.config.TaskConfig;
import com.om.DataMagic.common.util.HttpClientUtil;
import com.om.DataMagic.common.util.ObjectMapperUtil;
import com.om.DataMagic.domain.codePlatform.gitcode.primitive.GitCodeConstant;
import org.apache.http.Header;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class GiteeClient {


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
    private static final Logger LOGGER = LoggerFactory.getLogger(GiteeClient.class);

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
            URIBuilder uriBuilder = new URIBuilder(baseApiConfig.getGitee() + path);
            if (params != null && !params.isEmpty()) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    uriBuilder.addParameter(entry.getKey(), entry.getValue());
                }
            }
            url = uriBuilder.build().toString();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        Header header = new BasicHeader("Authorization", "Bearer " + accessConfig.getGitee());

        String response = "";
        try {
            response = client.getHttpClient(url, header);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        return response;
    }

    /**
     * 分页获取仓库所有者下的某个仓库的star数据
     * @param ownerName 仓库所有者
     * @param repoName 仓库名称
     * @return star数据字符串
     */
    public List<ArrayNode> getStarInfo(String ownerName, String repoName) {
        return geGitCodeArrayNodeByApi("/repos/%s/%s/stargazers",ownerName,repoName);
    }

    /**
     * 分页获取仓库所有者下的某个仓库的watch数据
     * @param ownerName 仓库所有者
     * @param repoName 仓库名称
     * @return issue数据字符串
     */
    public List<ArrayNode> getWatchInfo(String ownerName, String repoName) {
        return geGitCodeArrayNodeByApi("/repos/%s/%s/subscribers",ownerName,repoName);
    }
    /**
     * 分页获取仓库所有者下的某个仓库的fork数据
     * @param ownerName 仓库所有者
     * @param repoName 仓库名称
     * @return fork数据字符串
     */
    public List<ArrayNode> getForkInfo(String ownerName, String repoName) {
        return geGitCodeArrayNodeByApi("/repos/%s/%s/forks",ownerName,repoName);
    }

    /**
     * 分页获取仓库所有者下的某个仓库的分页接口的数据
     * @param ownerName 仓库所有者
     * @param repoName 仓库名称
     * @return 接口返回的json数据字符串
     */
    public List<ArrayNode> geGitCodeArrayNodeByApi(String api,String ownerName, String repoName) {
        String path = String.format(api, ownerName, repoName);
        Map<String,String> params = new HashMap<>();
        int page = 1;
        params.put("per_page", String.valueOf(GitCodeConstant.MAX_PER_PAGE));
        params.put("access_token",accessConfig.getGitee());
        List<ArrayNode> list = new ArrayList<>();
        while (true) {
            params.put("page", String.valueOf(page));
            String jsonInfo = callApiByPlatform(path,params);
            try {
                ArrayNode object = ObjectMapperUtil.toObject(ArrayNode.class, jsonInfo);
                list.add(object);
                if (object.size() < GitCodeConstant.MAX_PER_PAGE) {
                    break;
                }
            }catch (Exception e) {
                LOGGER.error("api:{},response:{}",path,jsonInfo);
                LOGGER.error("接口获取数据失败");
                break;
            }
            page++;
        }
        return list;
    }

}
