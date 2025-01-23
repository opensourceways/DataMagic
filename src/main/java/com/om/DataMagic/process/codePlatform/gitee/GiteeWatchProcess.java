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
import com.om.DataMagic.client.codePlatform.gitee.GiteeClient;
import com.om.DataMagic.common.util.ObjectMapperUtil;
import com.om.DataMagic.domain.codePlatform.gitcode.primitive.CodePlatformEnum;
import com.om.DataMagic.domain.codePlatform.gitcode.primitive.GitCodeConstant;
import com.om.DataMagic.infrastructure.pgDB.converter.WatchConverter;
import com.om.DataMagic.infrastructure.pgDB.dataobject.RepoDO;
import com.om.DataMagic.infrastructure.pgDB.dataobject.StarDO;
import com.om.DataMagic.infrastructure.pgDB.dataobject.WatchDO;
import com.om.DataMagic.infrastructure.pgDB.service.RepoService;
import com.om.DataMagic.infrastructure.pgDB.service.WatchService;
import com.om.DataMagic.process.DriverManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * watch application service
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
     * @param repoDO 仓库信息
     * @return Watch信息字符串
     */
    private List<WatchDO> getWatchList(RepoDO repoDO) {
        return formatStr(repoDO, client.getWatchInfo(repoDO.getOwnerName(), repoDO.getRepoName()));
    }
    /**
     * 转化并组装WatchDO数据
     * @param repoDO     仓库信息
     * @param arrayNodeList watch信息
     * @return watchdo 对象
     */

    private List<WatchDO> formatStr(RepoDO repoDO, List<ArrayNode> arrayNodeList) {
        List<WatchDO> watchDOList = new ArrayList<>();
        for (ArrayNode arrayNode : arrayNodeList) {
            watchDOList.addAll(converter.toDOList(arrayNode, repoDO.getOwnerName(),repoDO.getRepoName(), CodePlatformEnum.GITEE.getText()));
        }
        return watchDOList;
    }
}
