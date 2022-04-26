package com.ry.designpatterns.iterator;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * @author ryang
 * @Description
 * @date 2022年04月25日 11:21 上午
 */
public class ArrayIterator<E> implements Iterator<E> {
    private int cursor;
    private ArrayList<E> arrayList;

    public ArrayIterator(ArrayList<E> arrayList) {
        this.cursor = 0;
        this.arrayList = arrayList;
    }

    @Override
    public boolean hasNext() {
        return cursor != arrayList.size();
    }

    @Override
    public void next() {
        cursor++;
    }

    @Override
    public E currentItem() {
        if (cursor >= arrayList.size()) {
            throw new NoSuchElementException();
        }
        return arrayList.get(cursor);
    }

    public static void main(String[] args) {
        ArrayList<String> arrayList = Lists.newArrayList("a", "b", "c");
        ArrayIterator<String> iterator = new ArrayIterator<>(arrayList);
        while (iterator.hasNext()) {
            System.out.println(iterator.currentItem());
            iterator.next();
        }
    }
}
