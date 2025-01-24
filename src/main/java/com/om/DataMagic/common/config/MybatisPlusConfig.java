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

package com.om.DataMagic.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.DynamicTableNameInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.om.DataMagic.common.constant.TableConstant;
import com.om.DataMagic.common.handler.PlatformTableNameHandler;

@Configuration
public class MybatisPlusConfig {
    /**
     * Configures a MyBatis Plus interceptor.
     *
     * @return The configured MybatisPlusInterceptor bean.
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        DynamicTableNameInnerInterceptor dynamicTableNameYearInnerInterceptor = new DynamicTableNameInnerInterceptor();
        dynamicTableNameYearInnerInterceptor.setTableNameHandler(
                new PlatformTableNameHandler(TableConstant.PLATFORM_USER, TableConstant.DWS_USER,
                        TableConstant.DWS_CONTRIB));
        interceptor.addInnerInterceptor(dynamicTableNameYearInnerInterceptor);

        PaginationInnerInterceptor paginationInterceptor = new PaginationInnerInterceptor();
        interceptor.addInnerInterceptor(paginationInterceptor);
        return interceptor;
    }
}
