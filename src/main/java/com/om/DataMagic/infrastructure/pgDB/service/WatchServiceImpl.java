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

package com.om.DataMagic.infrastructure.pgDB.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.om.DataMagic.infrastructure.pgDB.dataobject.StarDO;
import com.om.DataMagic.infrastructure.pgDB.dataobject.WatchDO;
import com.om.DataMagic.infrastructure.pgDB.mapper.StarDOMapper;
import com.om.DataMagic.infrastructure.pgDB.mapper.WatchDOMapper;
import org.springframework.stereotype.Service;

/**
 * repo service 实现类
 *
 * @author zhaoyan
 * @since 2025-01-14
 */
@Service
public class WatchServiceImpl extends ServiceImpl<WatchDOMapper, WatchDO> implements WatchService {
}