package com.ry.designpatterns.interceptor.v2;

import java.util.Objects;

/**
 * 以链表方式实现执行器链
 * @author ryang
 * @Description
 * @date 2022年04月21日 7:37 下午
 */
public class HandlerChain {
    private Handler head = null;
    private Handler tail = null;

    /**
     * 添加处理器，维护处理器链中链表顺序
     * @author ryang
     * @date 2022/4/21 7:56 下午
     * @param handler
     */
    public void addHandler(Handler handler) {
        handler.setSuccessor(null);

        if (Objects.isNull(head)) {
            head = handler;
            tail = handler;
            return;
        }
        tail.setSuccessor(handler);
        tail = handler;
    }

    /**
     * 处理器链中处理器逻辑执行
     * @author ryang
     * @date 2022/4/21 7:57 下午
     */
    public void handle() {
        if (Objects.nonNull(head)) {
            head.handle();
        }
    }

    public static void main(String[] args) {
        HandlerChain chain = new HandlerChain();
        chain.addHandler(new HandlerA());
        chain.addHandler(new HandlerB());
        chain.handle();
    }
}
