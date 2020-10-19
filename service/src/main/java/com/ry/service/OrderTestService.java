package com.ry.service;

import com.ry.entity.OrderTest;
import com.ry.entity.ProductTest;
import com.ry.mapper.OrderTestMapper;
import com.ry.mapper.ProductTestMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;

/**
 * Description: 请描述你的文件
 *
 * @author renyang
 * @date 2020-09-01
 * <p>
 * All rights Reserved, Designed www.xiao100.com
 */
@Service
@Slf4j
public class OrderTestService {

    @Autowired
    private OrderTestMapper orderTestMapper;

    @Autowired
    private ProductTestMapper productTestMapper;

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    @Autowired
    private TransactionDefinition transactionDefinition;

    private static final int id = 1;
    private static final int count = 1;

    /**
     * 创建订单
     */
    @Transactional(rollbackFor = Exception.class)
    public int createOrder() {
        ProductTest productTest = productTestMapper.selectById(1);
        if (null == productTest) {
            throw new RuntimeException("商品不存在");
        }

        // 校验库存
        if (productTest.getCount() - count < 0) {
            throw new RuntimeException("库存不足");
        }

        // 扣减库存
        int result = productTestMapper.updateCount(id, -count);
        log.info("更新库存结果:{}", result);
//        if (productTestMapper.selectById(1).getCount() < 0) {
//            throw new RuntimeException("库存不足");
//        }

        if (result <=0) {
            throw new RuntimeException("库存不足");
        }

        // 创建订单
        OrderTest orderTest = new OrderTest();
        orderTest.setUserId(111);
        orderTest.setProductId(id);
        orderTest.setCount(count);
        orderTestMapper.insert(orderTest);
        log.info("订单编号:{}", orderTest.getId());
        return orderTest.getId();
    }

    /**
     * 创建订单
     *
     * @return
     */
    public synchronized int createOrderUseSynchronized() {
        return createOrder1();
    }

    /**
     * 创建订单
     */
    @Transactional(rollbackFor = Exception.class)
    public synchronized int createOrder1() {
        log.info(Thread.currentThread() + "获得锁");
        ProductTest productTest = productTestMapper.selectById(1);
        if (null == productTest) {
            throw new RuntimeException("商品不存在");
        }

        log.info("库存数:{}", productTest.getCount());
        // 校验库存
        if (productTest.getCount() - count < 0) {
            throw new RuntimeException("库存不足");
        }

        // 扣减库存
        productTest.setCount(productTest.getCount() - count);
        int result = productTestMapper.updateById(productTest);
        log.info("更新库存结果:{}", result);

        // 创建订单
        OrderTest orderTest = new OrderTest();
        orderTest.setUserId(111);
        orderTest.setProductId(id);
        orderTest.setCount(count);
        orderTestMapper.insert(orderTest);
        log.info(Thread.currentThread() + "释放锁");
        return orderTest.getId();
    }

    /**
     * 创建订单
     */
//    @Transactional(rollbackFor = Exception.class)
    public synchronized int createOrder2() {

        log.info(Thread.currentThread() + "获得锁");
        TransactionStatus transaction1 = null;
        try {
            transaction1 = platformTransactionManager.getTransaction(transactionDefinition);
            ProductTest productTest = productTestMapper.selectById(1);
            if (null == productTest) {
                throw new RuntimeException("商品不存在");
            }

            log.info("库存数:{}", productTest.getCount());
            // 校验库存
            if (productTest.getCount() - count < 0) {
                throw new RuntimeException("库存不足");
            }

            // 扣减库存
            productTest.setCount(productTest.getCount() - count);
            int result = productTestMapper.updateById(productTest);
            platformTransactionManager.commit(transaction1);
            log.info("更新库存结果:{}", result);
        }catch (Exception e){
            log.error("创建订单失败" ,e);
            platformTransactionManager.rollback(transaction1);
            throw new RuntimeException(e.getMessage(), e);
        }

        int result = 0;
        try {
            transaction1 = platformTransactionManager.getTransaction(transactionDefinition);
            // 创建订单
            OrderTest orderTest = new OrderTest();
            orderTest.setUserId(111);
            orderTest.setProductId(id);
            orderTest.setCount(count);
            orderTestMapper.insert(orderTest);
            platformTransactionManager.commit(transaction1);
            result = orderTest.getId();
        }catch (Exception e) {
            log.error("创建订单失败" ,e);
            platformTransactionManager.rollback(transaction1);
            throw new RuntimeException(e.getMessage(), e);
        }
        log.info(Thread.currentThread() + "释放锁");
        return result;
    }


}
