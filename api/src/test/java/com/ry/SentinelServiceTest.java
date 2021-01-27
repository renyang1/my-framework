package com.ry;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.ry.service.SentinelService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
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

        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule1 = new FlowRule();
        rule1.setResource("sentinelTest1");
        // QPS控制在2以内
        rule1.setCount(2);
        // QPS限流
        rule1.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule1.setLimitApp("default");
        rules.add(rule1);
        FlowRuleManager.loadRules(rules);

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
