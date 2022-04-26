package com.ry.designpatterns.interceptor.v1;

/**
 * 处理器抽象父类
 * @author ryang
 * @Description
 * @date 2022年04月21日 7:24 下午
 */
public abstract class Handler {
    protected Handler successor = null;

    public void setSuccessor(Handler handler) {
        this.successor = handler;
    }

    /**
     * 抽象方法，处理器执行逻辑
     * @author ryang
     * @date 2022/4/21 7:26 下午
     */
    public abstract void handle();
}
