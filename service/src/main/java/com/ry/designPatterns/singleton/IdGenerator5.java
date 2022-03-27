package com.ry.designPatterns.singleton;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author ryang
 * @Description 枚举实现单例
 * @date 2022年03月27日 3:00 下午
 */
public enum IdGenerator5 {
    INSTANCE;

    private AtomicLong id = new AtomicLong(0);
    public long getId() {
        return id.incrementAndGet();
    }
}
