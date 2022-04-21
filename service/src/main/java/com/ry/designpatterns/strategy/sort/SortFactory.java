package com.ry.designpatterns.strategy.sort;

import java.util.HashMap;
import java.util.Map;

/**
 * 排序策略工厂
 * @author ryang
 * @Description
 * @date 2022年04月19日 7:30 下午
 */
public class SortFactory {
    private static final Map<String, ISortAlg> alsMap = new HashMap<>();

    static {
        alsMap.put("QuickSort", new QuickSort());
        alsMap.put("ExternalSort", new ExternalSort());
        alsMap.put("MapReduceSort", new MapReduceSort());
    }

    public static ISortAlg getSortAlg(String type) {
        return alsMap.get(type);
    }
}
