package com.ry.designPatterns.adapter;

/**
 * C系统敏感词api
 * @author ryang
 * @Description
 * @date 2022年04月11日 11:21 上午
 */
public class CWordsFilter {

    /**
     * 输出用***替换敏感词后的文本
     * 
     * @author ryang
     * @date 2022/4/11 11:23 上午
     * @param text 原始文本
     * @param mark 指定替换后标记字符串
     * @return java.lang.String
     */
    public String filterWords(String text, String mark) {
        System.out.println("AWordsFilter#filterWords()...");
        return text;
    }
}
