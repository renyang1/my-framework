package com.ry.designpatterns.observer;

/**
 * @author ryang
 * @Description
 * @date 2022年04月12日 11:47 上午
 */
public class Demo {
    public static void main(String[] args) {
        ConcreteSubject subject = new ConcreteSubject();
        subject.registerObserver(new OneObserver());
        subject.registerObserver(new TwoObserver());
        subject.notifyObservers("hello world...");
    }
}
