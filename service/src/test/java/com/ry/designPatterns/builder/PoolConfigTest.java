package com.ry.designPatterns.builder;

import org.apache.commons.collections.CollectionUtils;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.*;

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


    @Test
    public void t1() {
        int[] INFRINGEMENT_LABEL = {446, 604};
        List<int[]> ints = Collections.singletonList(INFRINGEMENT_LABEL);
        List<Integer> ints1 = Arrays.asList(446, 604);
        ints1.forEach(a-> {
            System.out.println(a);
        });

//        CollectionUtils.intersection(tagIds, ))
    }
}