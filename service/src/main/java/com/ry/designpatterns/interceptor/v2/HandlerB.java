package com.ry.designpatterns.interceptor.v2;

/**
 * @author ryang
 * @Description
 * @date 2022年04月21日 7:31 下午
 */
public class HandlerB extends Handler {

    @Override
    public boolean doHandle() {
        System.out.println("HandlerB#handle()...");
        boolean handled = false;
        // ...业务逻辑
        return handled;
    }
}
