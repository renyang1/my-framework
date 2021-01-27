package com.ry.service;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 请描述你的文件
 *
 * @author renyang
 * @date 2020-10-21
 * <p>
 * All rights Reserved, Designed www.xiao100.com
 */
@Service
@Slf4j
public class SentinelService {

    public static void main(String[] args) {
        SentinelService service = new SentinelService();
        // 配置规则
        service.initFlowQpsRule("sentinelTest2");
        service.sentinelTest2();
    }

    /**
     * Sentinel测试方法
     */
    public void sentinelTest() {
        // 配置规则
        initFlowQpsRule("helloWorld");

        while (true) {
            try (Entry entry = SphU.entry("helloWorld")) {
                // 被保护的逻辑
                System.out.println("Hello world");
            } catch (BlockException e) {
                // 处理被流控的逻辑
                System.out.println("BlockEd!");
            }
        }
    }

    /**
     * Sentinel测试方法
     */
    @SentinelResource(value = "sentinelTest2")
    public void sentinelTest2() {

        while (true) {
            // 被保护的逻辑
            System.out.println("Hello world");
        }
    }

    /**
     * 使用注解方式实现方法限流
     *
     * @param orderId
     */
    @SentinelResource(value = "sentinelTest1", blockHandler = "sentinelTest1handleFlowQpsException",
            fallback = "sentinelTest1Fallback")
    public String sentinelTest1(String orderId) {

        if (("2").equals(orderId)) {
            throw new RuntimeException("出错了");
        }
        log.info("获取订单信息成功,订单编号{}", orderId);
        return "获取订单信息成功,订单编号{}" + orderId;
    }

    /**
     * 限流或降级处理逻辑
     *
     * @param orderId
     * @return
     */
    public String sentinelTest1handleFlowQpsException(String orderId) {
        log.error("handleFlowQpsException");
        return "handleFlowQpsException" + orderId;
    }

    /**
     * 业务逻辑抛出异常时提供fallback处理
     *
     * @param orderId
     * @param e
     * @return
     */
    public String sentinelTest1Fallback(String orderId, Throwable e) {
        log.error("sentinelTest1Fallback", e);
        return "sentinelTest1Fallback" + orderId;
    }

    public void initFlowQpsRule(String resource) {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule1 = new FlowRule();
        rule1.setResource(resource);
        // QPS控制在2以内
        rule1.setCount(5);
        // QPS限流
        rule1.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule1.setLimitApp("default");
        rules.add(rule1);
        FlowRuleManager.loadRules(rules);
    }

}
