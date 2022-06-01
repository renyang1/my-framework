package com.ry.spring.a03;

import com.ry.spring.a04.Bean2;
import com.ry.spring.a04.Bean3;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

@Component
public class Bean1 {
    private static final Logger log = LoggerFactory.getLogger(Bean1.class);

    private String port;

    @Autowired
    public void setPort(@Value ("${server.port}") String port) {
        log.info("@Value 生效: {}", port);
        this.port = port;
    }

    @PostConstruct
    public void init() {
        log.info("@PostConstruct 生效");
    }

    @PreDestroy
    public void destroy() {
        log.info("@PreDestroy 生效");
    }

    @Override
    public String toString() {
        return "Bean1{" +
               ", port='" + port + '\'' +
               '}';
    }
}
