package com.ry.designPatterns.adapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ryang
 * @Description
 * @date 2022年04月11日 11:29 上午
 */
public class AdaptorRiskManagement {

    List<IwordsFilter> filterWordsList = new ArrayList<>();

    /**
     * 若是在spring中，可以在初始化该bean的时候，将对象创建并完成注入，参考策略工厂的做法
     *
     * @author ryang
     * @date 2022/4/11 2:23 下午
     * @param wordsFilter
     */
    public void addFilterWords(IwordsFilter wordsFilter) {
        filterWordsList.add(wordsFilter);
    }

    public String filterWords(String text) {
        for (IwordsFilter wordsFilter : filterWordsList) {
            text = wordsFilter.filter(text);
        }
        return text;
    }
}
