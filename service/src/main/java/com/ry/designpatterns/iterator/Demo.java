package com.ry.designpatterns.iterator;

import com.google.common.collect.Lists;

import java.util.Iterator;
import java.util.List;

/**
 * @author ryang
 * @Description
 * @date 2022年04月25日 8:58 上午
 */
public class Demo {

    public static void main(String[] args) {
        List<String> list = Lists.newArrayList("a", "b", "c", "d");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            list.add("e");
            list.remove("a");
        }
    }

}
