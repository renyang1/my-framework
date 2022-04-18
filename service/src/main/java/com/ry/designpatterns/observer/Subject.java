package com.ry.designpatterns.observer;

/**
 * 主题接口（被观察者）
 * @author ryang
 * @Description
 * @date 2022年04月12日 11:32 上午
 */
public interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(String message);
}
