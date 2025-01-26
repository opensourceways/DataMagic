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
import com.om.DataMagic.infrastructure.pgDB.dataobject.WatchDO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * watch 转换器.
 *
 * @author pengyue
 * @since 2025-01-15
 */
@Component
public class WatchConverter {

    /**
     * @param arrayNode JSON对象。
     * @param owner 仓库所有者
     * @param repo 仓库所有者名称
     * @param codePlatForm 平台
     * @return List<WatchDO>
     */
    public List<WatchDO> toDOList(ArrayNode arrayNode, String owner, String repo, String codePlatForm) {
        List<WatchDO> starDOList = new ArrayList<>();
        for (JsonNode repoNode : arrayNode) {
            starDOList.add(toDO(repoNode, owner, repo, codePlatForm));
        }
        return starDOList;
    }


    /**
     * @param repoJson JSON对象.
     * @param owner 仓库所有者
     * @param repo 仓库所有者名称
     * @param codePlatForm 平台
     * @return WatchDO
     */
    public WatchDO toDO(JsonNode repoJson, String owner, String repo, String codePlatForm) {
        WatchDO watchDO = new WatchDO();
        watchDO.setUuid(codePlatForm + "-" + repoJson.path("id").asText());
        watchDO.setId(repoJson.path("id").asText());
        watchDO.setUserName(repoJson.path("name").asText());
        watchDO.setUserLogin(repoJson.path("login").asText());
        String dateString = repoJson.get("watch_at") == null ? null : repoJson.get("watch_at").asText();

        if (null != dateString) {
            watchDO.setCreatedAt(DateUtil.parse(dateString));
        }
        watchDO.setRepoPath(String.format("/%s/%s", owner, repo));
        watchDO.setType(repoJson.path("type") == null ? null : repoJson.path("type").asText());
        watchDO.setNamespace(owner);
        watchDO.setCodePlatform(codePlatForm);
        return watchDO;
    }
}
