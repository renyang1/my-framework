package com.ry.designpatterns.singleton;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author ryang
 * 静态内部类式单例
 * @date 2022年03月27日 2:23 下午
 */
public class IdGenerator4 {
    private AtomicLong id = new AtomicLong(0);
    private IdGenerator4() {}

    private static class SingletonHolder {
        private static final IdGenerator4 instance = new IdGenerator4();
    }

    public static IdGenerator4 getInstance() {
        return SingletonHolder.instance;
    }

    public long getId() {
        return id.incrementAndGet();
    }
}
