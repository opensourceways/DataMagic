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

package com.om.DataMagic.infrastructure.pgDB.service.dws;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.om.DataMagic.infrastructure.pgDB.dataobject.dws.UserDO;
import com.om.DataMagic.infrastructure.pgDB.mapper.dws.UserDOMapper;

@Service
public class UserServiceImpl extends ServiceImpl<UserDOMapper, UserDO> implements UserService {
    /**
     * List user info.
     *
     * @param queryWrapper The query wrapper
     * @return A list of users
     */
    @Override
    public List<UserDO> list(Wrapper<UserDO> queryWrapper) {
        return super.list(queryWrapper);
    }
}
