package com.ry.designpatterns.strategy.sort;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ryang
 * @Description
 * @date 2022年04月19日 7:49 下午
 */
public class Sorter {
    private static final long GB = 1000 * 1000 * 1000;
    // algs为查表法中的表
    private static final List<AlgRange> algs = new ArrayList<>();

    static {
        algs.add(new AlgRange(0, 6 * GB, SortFactory.getSortAlg("QuickSort")));
        algs.add(new AlgRange(6 * GB, 10 * GB, SortFactory.getSortAlg("ExternalSort")));
        algs.add(new AlgRange(10, 100 * GB, SortFactory.getSortAlg("MapReduceSort")));
    }

    public void sortFile(String filePath) {
        File file = new File(filePath);
        long length = file.length();
        ISortAlg sortAlg = null;
        for (AlgRange alg : algs) {
            if (alg.isRange(length)) {
                sortAlg = alg.getISortAlg();
                break;
            }
        }
        sortAlg.sort(filePath);
    }

}
