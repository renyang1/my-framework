package com.ry.designPatterns.strategy;

public class NormalDiscountStrategy implements DiscountStrategy{

    @Override
    public double calDiscount(Order order) {
        System.out.println("NormalDiscountStrategy: calDiscount()");
        return 100;
    }
}
