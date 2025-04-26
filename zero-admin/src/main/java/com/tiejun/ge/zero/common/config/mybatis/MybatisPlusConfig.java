package com.tiejun.ge.zero.common.config.mybatis;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: zero
 * @description:
 * @author: getiejun
 * @create: 2025-04-23 23:09
 **/
@Configuration
public class MybatisPlusConfig {

    /**
     * 添加分页插件
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 添加分页过滤器，如果mybatisplus需要使用分页，boot必须添加这个配置
        PaginationInnerInterceptor paginationInnerInterceptor =  new PaginationInnerInterceptor(DbType.MYSQL);
        // 溢出总页数后是否进行处理
        paginationInnerInterceptor.setOverflow(true);
        paginationInnerInterceptor.setMaxLimit(-1L);
        interceptor.addInnerInterceptor(paginationInnerInterceptor);
        return interceptor;
    }
}
