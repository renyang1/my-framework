package com.ry.designpatterns.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ryang
 * @Description
 * @date 2022年04月12日 11:44 上午
 */
public class ConcreteSubject implements Subject{
    private List<Observer> observers = new ArrayList<>();

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.doHandle(message);
        }
    }
}
