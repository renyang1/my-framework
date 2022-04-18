package com.ry.designpatterns.observer.register;

/**
 * 注册成功观察者接口定义
 * @author ryang
 * @Description
 * @date 2022年04月13日 2:49 下午
 */
public interface RegisterObserver {
    void handleRegisterSuccess(String userNo);
}
