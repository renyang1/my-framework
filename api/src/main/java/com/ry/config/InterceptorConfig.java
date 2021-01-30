package com.ry.config;

import com.ry.interceptor.LogInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Description: 请描述你的文件
 *
 * @author renyang
 * @date 2021-01-29
 * <p>
 * All rights Reserved, Designed www.xiao100.com
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //需要拦截的路径，/**表示需要拦截所有请求
        String[] addPathPatterns ={"/**"};
        // 忽略的路径
        String[] excludePathPatterns ={};

        // 注册拦截器，可以注册多个
        registry.addInterceptor(logInterceptor())
                .addPathPatterns(addPathPatterns)
                .excludePathPatterns(excludePathPatterns);

        // 若需注册多个，可以在下面继续添加
    }

    /**
     * LogInterceptor在这里声明为Bean是为了解决在使用注解声明时LogInterceptor中无法自动注入Bean属性
     * */
    @Bean
    public LogInterceptor logInterceptor() {
        return new LogInterceptor();
    }
}
