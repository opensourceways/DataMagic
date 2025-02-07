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

package com.om.DataMagic.infrastructure.pgDB.service.platform;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.om.DataMagic.infrastructure.pgDB.dataobject.RepoDO;
import com.om.DataMagic.infrastructure.pgDB.mapper.RepoDOMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * repo service 实现类.
 *
 * @author zhaoyan
 * @since 2025-01-14
 */
@Service
public class RepoServiceImpl extends ServiceImpl<RepoDOMapper, RepoDO> implements RepoService {
    /**
     * @param entityList sava or update .
     * @return
     */
    @Override
    public boolean saveOrUpdateBatch(Collection<RepoDO> entityList) {
        return super.saveOrUpdateBatch(entityList);
    }

    /**
     * query return list.
     * @return list
     */
    @Override
    public List<RepoDO> list() {
        return super.list();
    }
}
