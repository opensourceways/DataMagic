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

import  com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.om.DataMagic.infrastructure.pgDB.dataobject.StarDO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * star 转换器
 *
 * @author pengyue
 * @since 2025-01-15
 */
@Component
public class StarConverter {

    /**
     * 将repo json数组转化为DO list
     * @param arrayNode json数组
     * @return DO list
     */
    public List<StarDO> toDOList(ArrayNode arrayNode,String owner,String repo,String codePlatForm){
        List<StarDO> starDOList = new ArrayList<>();
        for (JsonNode repoNode : arrayNode) {
            starDOList.add(toDO(repoNode,owner,repo, codePlatForm));
        }
        return starDOList;
    }

    /**
     * 将repo json数据转换为DO对象
     * @param repoJson repo json数据
     * @return OD 对象
     */
    public StarDO toDO(JsonNode repoJson,String owner,String repo,String codePlatForm){
        StarDO starDO = new StarDO();
        starDO.setUuid(codePlatForm+repoJson.path("id").asText());
        starDO.setId(repoJson.path("id").asText());
        starDO.setUserName(repoJson.path("name").asText());
        starDO.setUserLogin(repoJson.path("login").asText());
        String dateString = repoJson.get("star_at")==null?null:repoJson.get("star_at").asText();
        if (null!=dateString){
            DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
            ZonedDateTime zonedDateTime = ZonedDateTime.parse(dateString, formatter);
            Date date = Date.from(zonedDateTime.toInstant());
            starDO.setCreatedAt(date);
        }
        starDO.setRepoPath(String.format("/%s/%s",owner,repo));
        starDO.setType(repoJson.path("type")==null?null:repoJson.path("type").asText());

        starDO.setNamespace(owner);
        starDO.setCodePlatform(codePlatForm);
        return starDO;
    }
}