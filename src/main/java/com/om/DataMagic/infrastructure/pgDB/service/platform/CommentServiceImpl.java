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
import com.om.DataMagic.infrastructure.pgDB.dataobject.CommentDO;
import com.om.DataMagic.infrastructure.pgDB.mapper.CommentDOMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * comment service 实现类.
 *
 * @author zhaoyan
 * @since 2025-01-17
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentDOMapper, CommentDO> implements CommentService {
    /**
     * @param entityList sava or update .
     * @return
     */
    @Override
    public boolean saveOrUpdateBatch(Collection<CommentDO> entityList) {
        return super.saveOrUpdateBatch(entityList);
    }

    /**
     * query return list.
     * @return list
     */
    @Override
    public List<CommentDO> list() {
        return super.list();
    }
}
