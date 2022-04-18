package com.ry.designpatterns.adapter;

/**
 * 类适配器：基于组合
 * @author 10023991
 */
public class AdaptorA implements ITarget {

    private Adaptee adaptee;

    public AdaptorA(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void a() {
        // 将a()委托给Adaptee的m1()执行
        adaptee.m1();
    }

    @Override
    public void b() {
        // 重写实现b()
        System.out.println("Adaptor:b()...");
    }

    /**
     * ITarget的m3()需要实现,委托给组合的类Adaptee对象执行
     */
    @Override
    public void m3() {
        adaptee.m3();
    }
}
