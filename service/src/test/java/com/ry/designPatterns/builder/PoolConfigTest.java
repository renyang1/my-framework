package com.ry.designPatterns.builder;

import org.junit.Test;

public class PoolConfigTest {

    @Test
    public void builder() {
        PoolConfig.Builder builder = new PoolConfig.Builder()
                .setName("ry-test")
                .setMinTotal(1)
                .setMaxTotal(2)
                .setIsDelete(false);
        PoolConfig poolConfig = builder.builder();
        System.out.println(poolConfig);
    }

    @Test
    public void poolConfigBuilder() {
        PoolConfigBuilder builder = new PoolConfigBuilder();
        builder.setName("ry-test")
                .setMinTotal(1)
                .setMaxTotal(2)
                .setIsDelete(false);
        // 因为目标类有参构造函数为protected，所以可以直接被其它类调用，
        // 不一定通过Builder类的build()方法调用，所有需要在有参构造函数中进行参数合法性校验
        PoolConfig config = new PoolConfig(builder);
        System.out.println(config);


        PoolConfig builder1 = builder.builder();
        System.out.println(builder1);
    }
}