package com.ry.designpatterns.strategy.sort;

/**
 * 排序接口
 * @author ryang
 * @Description
 * @date 2022年04月19日 7:17 下午
 */
public interface ISortAlg {
    /**
     * 对指定文件排序
     * @author ryang
     * @date 2022/4/19 7:18 下午
     * @param filePath
     */
    void sort(String filePath);
}
