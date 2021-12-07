package com.ry.config;

import com.ry.entity.Users;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description: 请描述你的文件
 *
 * @author renyang
 * @date 2021-01-15
 * <p>
 * All rights Reserved, Designed www.xiao100.com
 */
@Configuration
public class TestConfig {

    @Bean
    public Users creatIocService() {
        System.out.println("通过配置类方式创建Users bean");
        return new Users();
    }

}
