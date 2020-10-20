package com.ry.lock;

import com.ry.cache.MyRedisTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.script.RedisScript;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Description: 使用redis实现分布式锁工具类
 *
 * @author renyang
 * @date 2020-10-19
 * <p>
 * All rights Reserved, Designed www.xiao100.com
 */
@Slf4j
public class RedisLock implements AutoCloseable {

    private MyRedisTemplate myRedisTemplate;

    /**
     * 过期时间：秒
     */
    private int expireTime;

    private String key;

    private String value;

    public RedisLock(MyRedisTemplate myRedisTemplate, String key, int expireTime) {
        this.myRedisTemplate = myRedisTemplate;
        this.expireTime = expireTime;
        this.key = key;
        this.value = UUID.randomUUID().toString();
    }


    /**
     * 获取分布式锁
     *
     * @return
     */
    public boolean getLock() {
        return myRedisTemplate.setnx(key, value, expireTime, TimeUnit.SECONDS);
    }

    /**
     * 释放分布式锁
     *
     * @return
     */
    public Boolean unLock() {
        // lua脚本
        String script = "if redis.call(\"get\",KEYS[1]) == ARGV[1] then\n" +
                "    return redis.call(\"del\",KEYS[1])\n" +
                "else\n" +
                "    return 0\n" +
                "end";

        RedisScript<Boolean> redisScript = RedisScript.of(script, Boolean.class);
        List<String> keys = Arrays.asList(key);
        Boolean result = myRedisTemplate.execute(redisScript, keys, value);
        log.info("释放锁的结果："+result);
        return result;
    }

    @Override
    public void close() throws Exception {
        unLock();
    }
}
