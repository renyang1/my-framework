package com.ry.designPatterns.singleton;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 懒汉式单例
 * @author ryang
 * @date 2022年03月25日 4:02 下午
 */
public class IdGenerator2 {
    private AtomicLong id = new AtomicLong(0);
    private static IdGenerator2 instance;
    private IdGenerator2() {}
    public static synchronized IdGenerator2 getInstance() {
        if (instance == null) {
            instance = new IdGenerator2();
        }
        return instance;
    }

    public long getId() {
        return id.incrementAndGet();
    }
}
