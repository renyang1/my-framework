package com.ry.designpatterns.observer.register;

import org.springframework.stereotype.Service;

/**
 * @author ryang
 * @Description
 * @date 2022年04月12日 11:55 上午
 */
@Service
public class OneUserService {

    public String register(String phone, String password) {
        System.out.println("UserService#register()");
       return "no001";
    }
}
