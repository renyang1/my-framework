package com.ry.designpatterns.strategy;

public class PromotionDiscountStrategy implements DiscountStrategy{
    @Override
    public double calDiscount(Order order) {
        System.out.println("PromotionDiscountStrategy:calDiscount()");
        return 50;
    }
}
