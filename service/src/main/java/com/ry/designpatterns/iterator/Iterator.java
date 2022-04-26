package com.ry.designpatterns.iterator;

/**
 * @author ryang
 * @Description
 * @date 2022年04月25日 11:14 上午
 */
public interface Iterator<E> {
    boolean hasNext();
    void next();
    E currentItem();
}
