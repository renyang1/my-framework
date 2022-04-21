package com.ry.designpatterns.strategy.sort;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 查表法中的表中元素的基本构成，因为不再是由单一字段决定具体策略
 * @author ryang
 * @Description
 * @date 2022年04月19日 7:16 下午
 */
@Data
@AllArgsConstructor
public class AlgRange {
    private long start;
    private long end;
    private ISortAlg iSortAlg;

    public boolean isRange(long size) {
        return start <= size && size < end;
    }
}
