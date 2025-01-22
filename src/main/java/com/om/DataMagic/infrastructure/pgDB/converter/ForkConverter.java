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

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.om.DataMagic.common.util.DateUtil;
import com.om.DataMagic.infrastructure.pgDB.dataobject.ForkDO;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * fork 转换器
 *
 * @author pengyue
 * @since 2025-01-15
 */
@Component
public class ForkConverter {

    /**
     * 将fork json数组转化为DO list
     * @param arrayNode json数组
     * @return DO list
     */
    public List<ForkDO> toDOList(ArrayNode arrayNode,String owner,String repo,String codePlatForm){
        List<ForkDO> forkDOList = new ArrayList<>();
        for (JsonNode repoNode : arrayNode) {
            forkDOList.add(toDO(repoNode,owner,repo, codePlatForm));
        }
        return forkDOList;
    }

    /**
     * 将repo json数据转换为DO对象
     * @param repoJson repo json数据
     * @return OD 对象
     */
    public ForkDO toDO(JsonNode repoJson,String owner,String repo,String codePlatForm){
        ForkDO forkDO = new ForkDO();
        forkDO.setUuid(codePlatForm+"-"+repoJson.path("id").asText());
        forkDO.setId(repoJson.path("id").asText());
        forkDO.setFullName(repoJson.path("full_name").asText());
        forkDO.setUrl(repoJson.path("url").asText());
        forkDO.setNamespace(repoJson.path("namespace").path("path").asText());

        forkDO.setUserId(repoJson.path("owner").path("id").asText());
        forkDO.setUserName(repoJson.path("owner").path("name").asText());
        forkDO.setUserLogin(repoJson.path("owner").path("login").asText());

        forkDO.setRepoPath(repoJson.path("path").asText());
        forkDO.setRepoName(repoJson.path("name").asText());

        String dateString = repoJson.get("created_at")==null?null:repoJson.get("created_at").asText();
        if (null!=dateString){
            forkDO.setUpdatedAt(DateUtil.parse(dateString));
        }

        String updatedAt = repoJson.get("updated_at")==null?null:repoJson.get("updated_at").asText();
        if (null!=updatedAt){
            forkDO.setUpdatedAt(DateUtil.parse(updatedAt));
        }

        String pushedAt = repoJson.get("pushed_at")==null?null:repoJson.get("pushed_at").asText();
        if (null!=pushedAt){
            forkDO.setUpdatedAt(DateUtil.parse(pushedAt));
        }
        forkDO.setCodePlatform(codePlatForm);
        if (StringUtils.isEmpty(forkDO.getNamespace()) && "github".equals(codePlatForm)){
            //github 使用owner.login
            forkDO.setNamespace(repoJson.path("owner").path("login").asText());
        }
        if (StringUtils.isEmpty(forkDO.getRepoPath()) && "github".equals(codePlatForm)){
            //github 使用owner.login
            forkDO.setRepoPath(repo);
        }

        if ( "gitcode".equals(codePlatForm)){
            //github 使用owner.login
            forkDO.setRepoName(repo);
            forkDO.setRepoPath(repo);

        }

        return forkDO;
    }
}
