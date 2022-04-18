package com.ry.designpatterns.observer;

/**
 * @author ryang
 * @Description
 * @date 2022年04月12日 11:46 上午
 */
public class OneObserver implements Observer{
    @Override
    public void doHandle(String message) {
        System.out.println("OneObserver#doHandle()...");
    }
}
