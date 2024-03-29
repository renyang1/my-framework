package com.ry.config;

import org.aspectj.lang.annotation.DeclareWarning;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Description: 请描述你的文件
 *
 * @author renyang
 * @date 2021-01-15
 * <p>
 * All rights Reserved, Designed www.xiao100.com
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(User.class)
public class TestConfig {

//    @Bean
//    public User creatIocService() {
//        System.out.println("通过配置类方式创建Users bean");
//        User user = new User();
//        user.setName("ry");
//        user.setAge(16);
//        user.setCat(cat());
//        return user;
//    }

//    @Bean
//    public Cat cat() {
//        System.out.println("实例化Cat");
//        Cat tom = new Cat("tom", 2);
//        return tom;
//    }
}
