package com.ry.designPatterns.singleton;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 双重检测（支持延迟加载、高并发）
 * @author ryang
 * @date 2022年03月25日 4:02 下午
 */
public class IdGenerator3 {
    private AtomicLong id = new AtomicLong(0);
    private static volatile IdGenerator3 instance;
    private IdGenerator3() {}
    public static IdGenerator3 getInstance() {
        if (instance == null) {
            synchronized (IdGenerator3.class) {
                // 这里需要再次进行判空是因为并发时，可能多个线程第一次判空都为true,获取锁时发生阻塞等待
                if (instance == null) {
                    instance = new IdGenerator3();
                }
            }
        }
        return instance;
    }

    public long getId() {
        return id.incrementAndGet();
    }
}
