package com.summ.nanny;

import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@MapperScan("com.summ.nanny.mapper")
public class MybatisPlusConfig {

    /**
     * SQL执行效率插件
     */
    @Bean
    @Profile("debug")
    public PerformanceInterceptor performanceInterceptor() {
        return new PerformanceInterceptor().setFormat(true);
    }
}
