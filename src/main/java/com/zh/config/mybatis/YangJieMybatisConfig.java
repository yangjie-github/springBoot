package com.zh.config.mybatis;

import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;

/**
 * @author yangjie
 * 2019/3/31 12:00
 *
 * 自定义mybatis的配置规则
 */
@org.springframework.context.annotation.Configuration
public class YangJieMybatisConfig {

    //开启驼峰命名，也可以在配置文件中设置
    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return new ConfigurationCustomizer() {
            @Override
            public void customize(Configuration configuration) {
                configuration.setMapUnderscoreToCamelCase(true);
            }
        };
    }
}
