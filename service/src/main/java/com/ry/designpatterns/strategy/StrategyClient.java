package com.ry.designpatterns.strategy;

public class StrategyClient {

    public double discount(Order order) {
        DiscountStrategy discountStrategy = StrategyFactory.getDiscountStrategy(order.getType());
        return discountStrategy.calDiscount(order);
    }

    public static void main(String[] args) {
        double discount = new StrategyClient().discount(new Order(1000, "NORMAL"));
        System.out.println(discount);
    }
}
