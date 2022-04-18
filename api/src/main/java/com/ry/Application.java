package com.ry;

import com.ry.config.TestConfig;
import com.ry.config.TestConfig1;
import com.ry.config.User;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

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
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
//        User user1 = context.getBean(User.class);
//        User user2 = context.getBean(User.class);
//        System.out.println(user1==user2);

//        Cat cat1 = user1.getCat();
//        Cat cat2 = context.getBean(Cat.class);
//        System.out.println(cat1 == cat2);
//        TestConfig bean = context.getBean(TestConfig.class);
//        TestConfig1 bean1 = context.getBean(TestConfig1.class);
//        System.out.println(bean == null);
//        System.out.println(bean1 == null);
    }
}
