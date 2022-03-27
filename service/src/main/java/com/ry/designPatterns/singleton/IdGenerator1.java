package com.ry.designPatterns.singleton;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 饿汉式单例
 * @author ryang
 * @date 2022年03月25日 3:01 下午
 */
public class IdGenerator1 {
    private AtomicLong id = new AtomicLong(0);
    private static final IdGenerator1 instance = new IdGenerator1();
    private IdGenerator1() {}

    public static IdGenerator1 getInstance() {
        return instance;
    }

    public long getId() {
        return id.incrementAndGet();
    }
}
