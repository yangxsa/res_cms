package com.yaa.cms.config;

import com.yaa.cms.interceptor.BaseInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {


    /**
     * 配置静态访问资源 相当于 springmvc中<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }

    /**
     * 加载这个bean主要用于MyInterceptor中可以注入service或者dao
     * @return
     */
    @Bean
    public BaseInterceptor getMyInterceptor(){
        return new BaseInterceptor();
    }


    /**
     * 以前要访问一个页面需要先创建个Controller控制类，在写方法跳转到页面
     * 在这里配置后就不需要那么麻烦了，例如直接访问http://localhost:8080/toLogin就跳转到login.html页面了
     * @param registry
     */
    @Override
    protected void addViewControllers(ViewControllerRegistry registry) {
        //registry.addViewController("/toLogin").setViewName("login");
        super.addViewControllers(registry);
    }
    /**
     * 拦截器，用于配置拦截规则，相当于 <mvc:interceptor>******</mvc:interceptor>
     * @param registry
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getMyInterceptor())
                .addPathPatterns("/sys/**")
                .addPathPatterns("/bsy/**")
                .addPathPatterns("/index");
        super.addInterceptors(registry);
    }

}
