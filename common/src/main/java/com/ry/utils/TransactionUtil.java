package com.ry.utils;

import com.ry.functioninterface.Procedure;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;


/**
 *
 * 事务处理工具类
 *
 */
public class TransactionUtil {

    /**
     * 事务提交后异步执行方法
     * @param p
     */
    public static void afterCommit(Procedure p) {
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCommit() {
                p.run();
            }
        });
    }
}