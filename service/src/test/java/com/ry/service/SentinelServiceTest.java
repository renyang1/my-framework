package com.ry.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.*;

/**
 * Description: 请描述你的文件
 *
 * @author renyang
 * @date 2020-10-21
 * <p>
 * All rights Reserved, Designed www.xiao100.com
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SentinelServiceTest {

    @Autowired
    private SentinelService sentinelService;

    @Test
    public void sentinelTest1() throws Exception{

        // 定义栅栏，控制并发数
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10);

        // 倒计时门栓，用来控制主线程在子线程执行完成后再关闭
        CountDownLatch countDownLatch = new CountDownLatch(10);

        // 创建线程池
        ThreadPoolExecutor pool = new ThreadPoolExecutor(10, 20,
                60, TimeUnit.SECONDS, new LinkedBlockingDeque<>(1024),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        for (int i = 0; i < 10; i++) {
            final int j = i;
            pool.execute(() -> {
                        try {
                            cyclicBarrier.await();
                            sentinelService.sentinelTest1("" + j);
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            countDownLatch.countDown();
                        }
                    }
            );
        }
        // 这里若不使用倒计时门栓，会出现主线程已经执行完成，程序退出了，子线程的System.out.println(System.currentTimeMillis());还没执行
        countDownLatch.await();
        pool.shutdown();

    }


}
