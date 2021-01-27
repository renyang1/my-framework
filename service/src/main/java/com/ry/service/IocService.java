package com.ry.service;

import com.ry.utils.SpringUtil;
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
@Service
public class IocService {

    private List<String> myList = new ArrayList<>();

    @Value("${my-framework.name}${my-framework.nickName}")
    private String name;

    @PostConstruct
    public void testInitMethod() {
        myList.add(name);
        System.out.println(myList);
        System.out.println("初始化IocService类");
    }


}
