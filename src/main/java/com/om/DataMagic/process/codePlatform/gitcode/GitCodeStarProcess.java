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

package com.om.DataMagic.process.codePlatform.gitcode;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.om.DataMagic.client.codePlatform.gitcode.GitCodeService;
import com.om.DataMagic.infrastructure.pgDB.converter.StarConverter;
import com.om.DataMagic.infrastructure.pgDB.dataobject.RepoDO;
import com.om.DataMagic.infrastructure.pgDB.dataobject.StarDO;
import com.om.DataMagic.infrastructure.pgDB.service.RepoService;
import com.om.DataMagic.infrastructure.pgDB.service.StarService;
import com.om.DataMagic.process.DriverManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * pr application service
 *
 * @author pengyue
 * @since 2025-01-15
 */
@Component
public class GitCodeStarProcess implements DriverManager {

    @Autowired
    GitCodeService service;

    @Autowired
    StarConverter converter;

    @Autowired
    RepoService repoService;

    @Autowired
    StarService starService;
    /**
     * 执行 拉取并更新指定组织下仓库信息
     */
    @Override
    public void run() {
        List<RepoDO> repoDOList = repoService.list();
        List<StarDO> starDOList = new ArrayList<>();
        for (RepoDO repoDO : repoDOList) {
            starDOList.addAll(getStarList(repoDO));
        }
        starService.saveOrUpdateBatch(starDOList);
    }

    /**
     * 获取GitCode平台仓库下Star信息
     * @param repoDO 仓库信息
     * @return Star信息字符串
     */
    private List<StarDO> getStarList(RepoDO repoDO) {
        return formatStr(repoDO, service.getStarInfo(repoDO.getOwnerName(),repoDO.getRepoName()));
    }

    /**
     * 转化并组装StarDO数据
     * @param repoDO     仓库信息
     * @param arrayNodeList Star的ArryNode信息
     * @return Stardo 对象
     */
    private List<StarDO> formatStr(RepoDO repoDO, List<ArrayNode> arrayNodeList) {
        List<StarDO> prDOList = new ArrayList<>();
        for (ArrayNode arrayNode : arrayNodeList) {
            prDOList.addAll(converter.toDOList(arrayNode, repoDO.getOwnerName(),repoDO.getRepoName(),"gitcode"));
        }
        return prDOList;
    }
}
