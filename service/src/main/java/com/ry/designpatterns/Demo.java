package com.ry.designpatterns;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;

/**
 * @author ryang
 * @Description
 * @date 2022年04月27日 10:39 上午
 */
public class Demo {

    private static final Map<String, String> map = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        Executors.newFixedThreadPool(5).submit(() -> {
            System.out.println(System.currentTimeMillis());
            map.put("a", "a");
            System.out.println(System.currentTimeMillis());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

    }
}
