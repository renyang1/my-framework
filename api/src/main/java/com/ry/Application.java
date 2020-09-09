package com.ry;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Description: 请描述你的文件
 *
 * @author renyang
 * @date 2020-08-05
 * <p>
 * All rights Reserved, Designed www.xiao100.com
 */
@SpringBootApplication
@MapperScan("com.ry.mapper")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
