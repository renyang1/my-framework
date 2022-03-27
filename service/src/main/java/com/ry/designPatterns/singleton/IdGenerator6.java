package com.ry.designPatterns.singleton;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author ryang
 * @Description 线程唯一的单例类
 * @date 2022年03月27日 4:21 下午
 */
public class IdGenerator6 {
    private AtomicLong id = new AtomicLong(0);

    private static final ConcurrentHashMap<Long, IdGenerator6> instanceMap = new ConcurrentHashMap<>();
    private IdGenerator6() {}

    public static IdGenerator6 getInstance() {
        long currentThreadId = Thread.currentThread().getId();
        instanceMap.putIfAbsent(currentThreadId, new IdGenerator6());
        return instanceMap.get(currentThreadId);
    }

    public long getId() {
        return id.incrementAndGet();
    }
}
