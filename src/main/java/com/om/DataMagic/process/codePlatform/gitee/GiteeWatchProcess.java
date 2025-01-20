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

package com.om.DataMagic.process.codePlatform.gitee;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.om.DataMagic.client.codePlatform.gitcode.GitCodeClient;
import com.om.DataMagic.client.codePlatform.gitee.GiteeClient;
import com.om.DataMagic.common.util.ObjectMapperUtil;
import com.om.DataMagic.domain.codePlatform.gitcode.primitive.GitCodeConstant;
import com.om.DataMagic.infrastructure.pgDB.converter.PRConverter;
import com.om.DataMagic.infrastructure.pgDB.converter.WatchConverter;
import com.om.DataMagic.infrastructure.pgDB.dataobject.WatchDO;
import com.om.DataMagic.infrastructure.pgDB.dataobject.RepoDO;
import com.om.DataMagic.infrastructure.pgDB.service.PRService;
import com.om.DataMagic.infrastructure.pgDB.service.RepoService;
import com.om.DataMagic.infrastructure.pgDB.service.WatchService;
import com.om.DataMagic.process.DriverManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * watch application service
 *
 * @author pengyue
 * @since 2025-01-15
 */
@Component
public class GiteeWatchProcess implements DriverManager {

    @Autowired
    GiteeClient client;

    @Autowired
    WatchConverter converter;

    @Autowired
    RepoService repoService;

    @Autowired
    WatchService watchService;

    /**
     * 执行 拉取并更新指定组织下仓库信息
     */
    @Override
    public void run() {
        List<RepoDO> repoDOList = repoService.list();
        List<WatchDO> prList = new ArrayList<>();
        for (RepoDO repoDO : repoDOList) {
            prList.addAll(getWatchList(repoDO));
        }
        watchService.saveOrUpdateBatch(prList);
    }

    /**
     * 获取GitCode平台仓库下PR信息
     *
     * @param repoDO 仓库信息
     * @return PR信息字符串
     */
    private List<WatchDO> getWatchList(RepoDO repoDO) {
        List<String> watchArrayList = new ArrayList<>();
        int page = 1;
        while (true) {
            String prInfo = client.getWatchInfo(repoDO.getOwnerName(), repoDO.getRepoName(), page);
            if (GitCodeConstant.NULL_ARRAY_RESPONSE.equals(prInfo)) {
                break;
            }
            page++;
            watchArrayList.add(prInfo);
        }
        return formatStr(repoDO, watchArrayList);
    }

    /**
     * 转化并组装WatchDO数据
     *
     * @param repoDO     仓库信息
     * @param prInfoList pr信息字符串
     * @return WatchDO 对象
     */
    private List<WatchDO> formatStr(RepoDO repoDO, List<String> prInfoList) {
        List<ArrayNode> arrayNodeList = prInfoList.stream().map(
                prArray -> ObjectMapperUtil.toObject(ArrayNode.class, prArray)).toList();
        List<WatchDO> watchDOList = new ArrayList<>();
        for (ArrayNode arrayNode : arrayNodeList) {
            watchDOList.addAll(converter.toDOList(arrayNode, repoDO.getOwnerName(),repoDO.getRepoName(),"gitee"));
        }
        return watchDOList;
    }
}
