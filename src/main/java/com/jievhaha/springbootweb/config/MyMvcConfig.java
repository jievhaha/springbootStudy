package com.jievhaha.springbootweb.config;

import com.jievhaha.springbootweb.componet.MyInterceptor;
import com.jievhaha.springbootweb.componet.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 开启@EnableWebMvc会完全接管springboot中mvc的解析器
 * 不添加，相当于在原有基础上增加我们自己的解析器
 */
//@EnableWebMvc
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/jie").setViewName("success");
    }

    /**
     * WebMvcAutoConfiguration可查看
     * 有个内部类EnableWebMvcConfiguration
     *            继承了DelegatingWebMvcConfiguration
     *                 继承了WebMvcConfigurationSupport（addInterceptors）
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/index.html","/","/user/login");
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        return new MyMvcConfig(){
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                //ThymeleafAutoConfiguration会自动解析到classpath:/templates/*.html
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/main.html").setViewName("dashboard");
            }
        };
    }

    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }
}
