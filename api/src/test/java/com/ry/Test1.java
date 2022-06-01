package com.ry;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ryang
 * @Description
 * @date 2022年05月12日 5:57 下午
 */
public class Test1 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>() {{
            add("1");
            add("2");
        }};

        System.out.println(list.stream().map(a -> Integer.valueOf(a)).collect(Collectors.toList()));
    }
}
