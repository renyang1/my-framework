package com.ry.designpatterns.singleton;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ryang
 * @Description
 * @date 2022年03月27日 4:47 下午
 */
public class Logger {
    private String name;
    private static final ConcurrentHashMap<String, Logger> instanceMap = new ConcurrentHashMap<>();

    private Logger(String name) {
        this.name = name;
    }

    public static Logger getInstance(String loggerName) {
        instanceMap.putIfAbsent(loggerName, new Logger(loggerName));
        return instanceMap.get(loggerName);
    }

    public void log() {
        System.out.println(name);
    }
}
