package com.ry.designpatterns.interceptor.v1;

/**
 * @author ryang
 * @Description
 * @date 2022年04月21日 7:31 下午
 */
public class HandlerA extends Handler{

    @Override
    public void handle() {
        System.out.println("HandlerA#handle()...");
        boolean handled = false;
        // ...业务逻辑
        if (!handled && successor != null) {
            // 业务逻辑执行完成后handled依然为false,则往下传递
            successor.handle();
        }
    }
}
