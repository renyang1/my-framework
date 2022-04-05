package com.ry.designPatterns.proxy;

/**
 * 静态代理的实现方式2：继承被代理的类，真正的逻辑由父类执行
 * @author ryang
 * @Description
 * @date 2022年04月05日 10:10 下午
 */
public class UserControllerProxy1 extends UserController{

    @Override
    public void login(String userName, String password) {
        // 对被代理的父类进行增强
        System.out.println("UserControllerProxy1#login()...");
        // 委托父类执行真正的逻辑
        super.login(userName, password);
    }
}
