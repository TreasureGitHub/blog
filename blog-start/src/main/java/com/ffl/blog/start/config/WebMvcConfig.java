package com.ffl.blog.start.config;

import com.ffl.blog.start.interceptor.SecurityInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author lff
 * @datetime 2020/02/01 14:21
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SecurityInterceptor());
        super.addInterceptors(registry);
    }
}
