package com.ry.designpatterns.observer;

/**
 * @author ryang
 * @Description
 * @date 2022年04月12日 11:46 上午
 */
public class TwoObserver implements Observer{
    @Override
    public void doHandle(String message) {
        System.out.println("TwoObserver#doHandle()...");
    }
}
