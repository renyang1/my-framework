package com.ry.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Description: 请描述你的文件
 *
 * @author renyang
 * @date 2021-01-15
 * <p>
 * All rights Reserved, Designed www.xiao100.com
 */
@Slf4j
@Service
public class IocService {

    private List<String> myList = new ArrayList<>();

    @Value("${my-framework.name}${my-framework.nickName}")
    private String name;

    @PostConstruct
    public void testInitMethod() {
        myList.add(name);
        log.info("初始化IocService类");
    }

    public void test() {
        log.info("IocService: test()");
    }

}
