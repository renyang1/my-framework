package com.ry;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;

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
