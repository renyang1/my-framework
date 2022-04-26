package com.ry.designpatterns.interceptor.v2;

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
     * 抽象方法，处理器执行逻辑(模版模式)
     * @author ryang
     * @date 2022/4/21 7:26 下午
     */
    public final void handle() {
        boolean handled = doHandle();
        if (!handled && successor != null) {
            successor.doHandle();
        }
    }

    public abstract boolean doHandle();
}
