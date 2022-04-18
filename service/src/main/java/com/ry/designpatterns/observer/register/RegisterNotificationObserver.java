package com.ry.designpatterns.observer.register;

import org.springframework.stereotype.Component;

/**
 * @author ryang
 * @Description
 * @date 2022年04月13日 3:01 下午
 */
@Component
public class RegisterNotificationObserver implements RegisterObserver {

    @Override
    public void handleRegisterSuccess(String userNo) {
        System.out.println("RegisterNotificationObserver#handleRegisterSuccess()...");
    }
}
