package com.yangjie.config.servlet;

import com.yangjie.config.servlet.filter.YangJieFilter;
import com.yangjie.config.servlet.listener.YangJieListener;
import com.yangjie.config.servlet.servlet.YangJieServlet;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * @author yangjie
 * 2019/3/27 21:41
 *
 * 注册三大组件
 * 配置嵌入式的servlet容器
 */
@Configuration
public class YangJieServerConfig {

    //注册servlet
    @Bean
    public ServletRegistrationBean yangJieServlet() {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new YangJieServlet(), "/servlet");
//        registrationBean.setLoadOnStartup(1);
        return registrationBean;
    }

//    注册filter
    @Bean
    public FilterRegistrationBean yangJieFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new YangJieFilter());
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/hello", "/servlet"));
        return filterRegistrationBean;
    }

    //注册listener
    @Bean
    public ServletListenerRegistrationBean yangJieListener() {
        ServletListenerRegistrationBean listenerRegistrationBean = new ServletListenerRegistrationBean<YangJieListener>(new YangJieListener());
        return listenerRegistrationBean;
    }

    //配置嵌入式的servlet容器
//    @Bean
//    public EmbeddedServletContainerCustomizer embeddedServletContainerCustomizer() {
//        return new EmbeddedServletContainerCustomizer() {
//            //定制嵌入式的servlet容器相关的规则
//            @Override
//            public void customize(ConfigurableEmbeddedServletContainer container) {
//                container.setPort(8085);
//            }
//        };
//    }
}
