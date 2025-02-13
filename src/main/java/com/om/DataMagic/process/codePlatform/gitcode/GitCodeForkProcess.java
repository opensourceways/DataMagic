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
import com.om.DataMagic.common.constant.CodePlatformEnum;
import com.om.DataMagic.infrastructure.pgDB.converter.ForkConverter;
import com.om.DataMagic.infrastructure.pgDB.dataobject.ForkDO;
import com.om.DataMagic.infrastructure.pgDB.dataobject.RepoDO;
import com.om.DataMagic.infrastructure.pgDB.service.platform.ForkService;
import com.om.DataMagic.infrastructure.pgDB.service.platform.RepoService;
import com.om.DataMagic.process.DriverManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * fork application service.
 *
 * @author pengyue
 * @since 2025-01-15
 */
@Component
public class GitCodeForkProcess implements DriverManager {

    /**
     * client gitcode接口统一调用客户端.
     */
    @Autowired
    private GitCodeService service;
    /**
     * converter json类型转换.
     */
    @Autowired
    private ForkConverter converter;
    /**
     * 仓库服务.
     */
    @Autowired
    private RepoService repoService;
    /**
     * fork服务.
     */
    @Autowired
    private ForkService forkService;

    /**
     * 执行 拉取并更新指定组织下仓库信息.
     */
    @Override
    public void run() {
        List<RepoDO> repoDOList = repoService.list();
        List<ForkDO> prList = new ArrayList<>();
        for (RepoDO repoDO : repoDOList) {
            prList.addAll(getForkList(repoDO));
        }
        forkService.saveOrUpdateBatch(prList);
    }

    /**
     * 获取GitCode平台仓库下Fork信息.
     *
     * @param repoDO 仓库信息
     * @return Fork信息字符串
     */
    private List<ForkDO> getForkList(RepoDO repoDO) {
        return formatStr(repoDO, service.getForkInfo(repoDO.getNamespace(), repoDO.getPath()));
    }

    /**
     * 转化并组装ForkDO数据.
     *
     * @param repoDO        仓库信息
     * @param arrayNodeList pr信息字符串
     * @return ForkDO 对象
     */
    private List<ForkDO> formatStr(RepoDO repoDO, List<ArrayNode> arrayNodeList) {
        List<ForkDO> prDOList = new ArrayList<>();
        for (ArrayNode arrayNode : arrayNodeList) {
            prDOList.addAll(converter.toDOList(arrayNode, repoDO.getNamespace(), repoDO.getPath(),
                    CodePlatformEnum.GITCODE.getText()));
        }
        return prDOList;
    }
}
