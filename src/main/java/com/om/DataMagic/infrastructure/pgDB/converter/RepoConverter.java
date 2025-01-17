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
import com.om.DataMagic.infrastructure.pgDB.dataobject.RepoDO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * repo 转换器
 *
 * @author zhaoyan
 * @since 2025-01-15
 */
@Component
public class RepoConverter {

    /**
     * 将repo json数组转化为DO list
     * @param arrayNode json数组
     * @return DO list
     */
    public List<RepoDO> toDOList(ArrayNode arrayNode){
        List<RepoDO> repoDOList = new ArrayList<>();
        for (JsonNode repoNode : arrayNode) {
            repoDOList.add(toDO(repoNode));
        }
        return repoDOList;
    }

    /**
     * 将repo json数据转换为DO对象
     * @param repoJson repo json数据
     * @return OD 对象
     */
    public RepoDO toDO(JsonNode repoJson){
        RepoDO repoDO = new RepoDO();
        repoDO.setRepoId(repoJson.path("id").asInt());
        repoDO.setRepoName(repoJson.path("name").asText());
        repoDO.setRepoUrl(repoJson.path("html_url").asText());
        repoDO.setOwnerName(repoJson.path("namespace").path("name").asText());
        repoDO.setUserId(repoJson.path("namespace").path("id").asInt());
        repoDO.setSigName("sigName");
        return repoDO;
    }
}
