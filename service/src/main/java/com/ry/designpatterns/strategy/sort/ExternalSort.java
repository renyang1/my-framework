package com.ry.designpatterns.strategy.sort;

/**
 * @author ryang
 * @Description
 * @date 2022年04月19日 7:20 下午
 */
public class ExternalSort implements ISortAlg{
    @Override
    public void sort(String filePath) {
        System.out.println("ExternalSort#sort()...");
    }
}
