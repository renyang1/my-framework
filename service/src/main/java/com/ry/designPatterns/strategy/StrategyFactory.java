package com.ry.designPatterns.strategy;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class StrategyFactory {

    private static final Map<String, DiscountStrategy> strategyMap = new HashMap<>();

    static {
        strategyMap.put("NORMAL", new NormalDiscountStrategy());
        strategyMap.put("PROMOTION", new PromotionDiscountStrategy());
    }

    public static DiscountStrategy getDiscountStrategy(String type) {
        if (StringUtils.isBlank(type)) {
            throw new IllegalArgumentException("类型不能为空");
        }
        return strategyMap.get(type);
     }
}
