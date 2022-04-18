package com.ry.designpatterns.proxy;

/**
 * @author ryang
 * @Description
 * @date 2022年04月05日 10:10 下午
 */
public class UserController implements IUserController{

    @Override
    public void login(String userName, String password) {
        System.out.println("UserController#login()...");
    }
}
