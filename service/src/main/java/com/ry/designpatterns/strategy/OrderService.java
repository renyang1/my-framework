package com.ry.designpatterns.strategy;

import java.util.Objects;

public class OrderService {

    public double discount1(Order order) {
        double discount = 0.0;
        String type = order.getType();
        if (Objects.equals(type, "NORMAL")) {
            // 普通订单计算逻辑
        }else if (Objects.equals(type, "PROMOTION")) {
            // 优惠订单计算逻辑
        }else {
            throw new RuntimeException("订单类型错误");
        }
        return discount;
    }

    public double discount(Order order) {
        DiscountStrategy discountStrategy = StrategyFactory.getDiscountStrategy(order.getType());
        return discountStrategy.calDiscount(order);
    }

    public static void main(String[] args) {
        double discount = new OrderService().discount(new Order(1000, "NORMAL"));
        System.out.println(discount);
    }
}
