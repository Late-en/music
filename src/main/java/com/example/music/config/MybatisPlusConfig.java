package com.example.music.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Late-en
 */
@Configuration
@MapperScan("com.example.music.mapper")
//开启全局事务，一般在service层加@Transaction
@EnableTransactionManagement
public class MybatisPlusConfig {
    /*
      注册bean让spring管理
      性能分析拦截器，不建议生产使用
     */
    /*
    @Bean
    public PerformanceInterceptor performanceInterceptor() {
        return new PerformanceInterceptor();
    }
     */

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
