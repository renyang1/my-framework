package com.ry.designPatterns.adapter;

/**
 * 类适配器：基于继承
 * @author 10023991
 */
public class Adaptor extends Adaptee implements ITarget {

    @Override
    public void a() {
        // 将a()委托给Adaptee的m1()执行
        super.m1();
    }

    @Override
    public void b() {
        // 重写实现b()
        System.out.println("Adaptor:b()...");
    }

    // ITarget的m3()不需要实现，直接继承Adaptee的m3()
}
