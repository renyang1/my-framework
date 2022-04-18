package com.ry.designpatterns.proxy;

/**
 * 静态代理的实现方式1：和被代理的类实现相同的接口
 * @author ryang
 * @Description
 * @date 2022年04月05日 10:10 下午
 */
public class UserControllerProxy implements IUserController{

    // 被代理的对象
    private UserController userController;

    public UserControllerProxy(UserController userController) {
        this.userController = userController;
    }

    @Override
    public void login(String userName, String password) {
        // 对被代理的类进行增强
        System.out.println("UserControllerProxy#login()...");
        // 委托被代理的对象执行真正的逻辑
        userController.login(userName, password);
    }
}
