package com.ry.designpatterns.interceptor.v3;

/**
 * @author ryang
 * @Description
 * @date 2022年04月21日 11:28 下午
 */
public class HandleB implements IHandler{

    @Override
    public boolean handle() {
        System.out.println("HandleB#handle()...");
        boolean handled = false;
        // ...业务逻辑
        return handled;
    }
}
