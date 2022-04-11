package com.ry.designPatterns.adapter;

/**
 * @author ryang
 * @Description
 * @date 2022年04月11日 11:29 上午
 */
public class RiskManagement {

    private AWordsFilter aWordsFilter = new AWordsFilter();
    private BWordsFilter bWordsFilter = new BWordsFilter();
    private CWordsFilter cWordsFilter = new CWordsFilter();

    public String filterWords(String text) {
        String maskedText = aWordsFilter.filterWords(text);
        maskedText = bWordsFilter.filterWords(maskedText);
        maskedText = cWordsFilter.filterWords(maskedText, "***");
        return maskedText;
    }
}
