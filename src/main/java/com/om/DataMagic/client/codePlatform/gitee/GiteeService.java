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
import com.om.DataMagic.common.config.TaskConfig;
import com.om.DataMagic.common.util.ObjectMapperUtil;
import com.om.DataMagic.domain.codePlatform.gitcode.primitive.GitCodeConstant;
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
 * @author pengyue
 * @since 2025-01-24
 */
@Service
public class GiteeService {

    /**
     * gitee client.
     */
    @Autowired
    private GiteeClient client;

    /**
     * 任务配置.
     */
    @Autowired
    private TaskConfig config;

    /**
     * Logger for logging messages in App class.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(GiteeService.class);


    /**
     * 分页获取仓库所有者下的某个仓库的star数据.
     *
     * @param ownerName 仓库所有者
     * @param repoName  仓库名称
     * @return star数据字符串
     */
    public List<ArrayNode> getStarInfo(String ownerName, String repoName) {
        return geGiteeArrayNodeByApi("/repos/%s/%s/stargazers", ownerName, repoName);
    }

    /**
     * 分页获取仓库所有者下的某个仓库的watch数据.
     *
     * @param ownerName 仓库所有者
     * @param repoName  仓库名称
     * @return issue数据字符串
     */
    public List<ArrayNode> getWatchInfo(String ownerName, String repoName) {
        return geGiteeArrayNodeByApi("/repos/%s/%s/subscribers", ownerName, repoName);
    }

    /**
     * 分页获取仓库所有者下的某个仓库的fork数据.
     *
     * @param ownerName 仓库所有者
     * @param repoName  仓库名称
     * @return fork数据字符串
     */
    public List<ArrayNode> getForkInfo(String ownerName, String repoName) {
        return geGiteeArrayNodeByApi("/repos/%s/%s/forks", ownerName, repoName);
    }

    /**
     * 分页获取仓库所有者下的某个仓库的分页接口的数据.
     *
     * @param ownerName 仓库所有者
     * @param repoName  仓库名称
     * @param api       仓库名称
     * @return 接口返回的json数据字符串
     */
    public List<ArrayNode> geGiteeArrayNodeByApi(String api, String ownerName, String repoName) {
        String path = String.format(api, ownerName, repoName);
        Map<String, String> params = new HashMap<>();
        int page = 1;
        params.put("per_page", String.valueOf(GitCodeConstant.MAX_PER_PAGE));
        params.put("access_token", config.getToken());
        List<ArrayNode> list = new ArrayList<>();
        while (true) {
            params.put("page", String.valueOf(page));
            try {
                String jsonInfo = client.callApi(path, params);
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
