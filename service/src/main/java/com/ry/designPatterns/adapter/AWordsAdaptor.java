package com.ry.designPatterns.adapter;

/**
 * @author ryang
 * @Description
 * @date 2022年04月11日 1:52 下午
 */
public class AWordsAdaptor implements IwordsFilter {

    private AWordsFilter aWordsFilter;
    public AWordsAdaptor(AWordsFilter aWordsFilter) {
        this.aWordsFilter = aWordsFilter;
    }
    @Override
    public String filter(String text) {
        return aWordsFilter.filterWords(text);
    }
}
