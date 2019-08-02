package com.yangjie.config.springmvc;

import com.yangjie.config.localei18n.YangJieLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author yangjie
 * 2019/3/25 21:00
 */


//使用WebMvcConfigurerAdapter可以扩展springmvc的配置功能，需要什么功能，重写什么方法就可以
//@EnableWebMvc注解会使我们全面接管springmvc的配置，spring-boot自带mvc的默认配置不生效（包括静态资源）
@Configuration
public class YangJieSpringMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry viewControllerRegistry) {
        //浏览器发送yangjie请求，也来success页面
        viewControllerRegistry.addViewController("yangjie").setViewName("login");
    }

    //所有的WebMvcConfigurerAdapter组件会一起一作用,这个要使用bean注册到容器中；
    @Bean
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter() {
        WebMvcConfigurerAdapter webMvcConfigurerAdapter = new WebMvcConfigurerAdapter() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/hello").setViewName("hello");
                registry.addViewController("/index").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/success.html").setViewName("success");
            }

            //注册拦截器, 以前的需要排除静态资源，现在不需要考虑
//            @Override
//            public void addInterceptors(InterceptorRegistry registry) {
//                registry.addInterceptor(new YangJieHandelIntercept())
//                        .addPathPatterns("/**")
//                        .excludePathPatterns("/index", "/user/login", "/hello");
//            }
        };


        return webMvcConfigurerAdapter;
    }

    //注入自己的localeResolver
    @Bean
    public LocaleResolver localeResolver() {
        return new YangJieLocaleResolver();
    }
}
