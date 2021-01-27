package com.ry.controller;

import com.ry.cache.MyRedisTemplate;
import com.ry.lock.RedisLock;
import com.ry.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description: 请描述你的文件
 *
 * @author renyang
 * @date 2020-10-19
 * <p>
 * All rights Reserved, Designed www.xiao100.com
 */
@RestController
@Slf4j
@RequestMapping("/lock")
public class LockController {

    @Autowired
    private MyRedisTemplate myRedisTemplate;

    private Lock lock = new ReentrantLock();

    @GetMapping("/redisLock")
    public Response redisLock() {
        log.info("调用接口{}", "/redisLock");

        try (RedisLock redisLock = new RedisLock(myRedisTemplate, "redisKey", 60*1000)) {
            if (redisLock.getLock()) {
                // 获取锁成功
                log.info("获取锁成功");
                Thread.sleep(10 * 1000);
            }
        }catch (Exception e) {
            log.error("", e);
        }
        log.info("执行完成");
        return Response.success();
    }

    @GetMapping("/singleLock")
    public Response singleLock() {

        log.info("进入方法{}", "singleLock");
        lock.lock();
        try {
            log.info("获取锁成功");
            Thread.sleep(60 * 1000);
        }catch (Exception e) {
            log.error("",e);
        }finally {
            lock.unlock();
        }
        log.info("执行完成");
        return Response.of("执行完成");
    }

}
