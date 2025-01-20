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
import com.om.DataMagic.infrastructure.pgDB.dataobject.WatchDO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * watch 转换器
 *
 * @author pengyue
 * @since 2025-01-15
 */
@Component
public class WatchConverter {

    /**
     * watch json数组转化为DO list
     * @param arrayNode json数组
     * @return DO list
     */
    public List<WatchDO> toDOList(ArrayNode arrayNode,String owner,String repo,String codePlatForm){
        List<WatchDO> starDOList = new ArrayList<>();
        for (JsonNode repoNode : arrayNode) {
            starDOList.add(toDO(repoNode,owner,repo,codePlatForm));
        }
        return starDOList;
    }

    /**
     * 将watch json数据转换为DO对象
     * @param repoJson repo json数据
     * @return OD 对象
     */
    public WatchDO toDO(JsonNode repoJson,String owner,String repo,String codePlatForm){
        WatchDO watchDO = new WatchDO();
        watchDO.setUuid(codePlatForm+repoJson.path("id").asText());
        watchDO.setId(repoJson.path("id").asText());
        watchDO.setUserName(repoJson.path("name").asText());
        watchDO.setUserLogin(repoJson.path("login").asText());
        String dateString = repoJson.get("watch_at")==null?null:repoJson.get("watch_at").asText();
        if (null!=dateString){
            LocalDate localDate = LocalDate.parse(dateString, DateTimeFormatter.ISO_DATE);
            Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            watchDO.setCreatedAt(date);
        }
        watchDO.setRepoPath(String.format("/%s/%s",owner,repo));
        watchDO.setType(repoJson.path("type")==null?null:repoJson.path("type").asText());
        watchDO.setNamespace(owner);
        watchDO.setCodePlatform(codePlatForm);
        return watchDO;
    }
}
