package com.ry.designPatterns.singleton;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author ryang
 * @Description
 * @date 2022年03月27日 4:32 下午
 */
public class BackendServer {
    private long serverNo;
    private String serverAddress;

    private static final int SERVER_COUNT = 3;
    private static final Map<Long, BackendServer> serverInstanceMap = new HashMap<>(3);

    static {
        serverInstanceMap.put(1L, new BackendServer(1L, "192.168.1.1"));
        serverInstanceMap.put(2L, new BackendServer(2L, "192.168.1.2"));
        serverInstanceMap.put(3L, new BackendServer(3L, "192.168.1.3"));
    }

    private BackendServer(long serverNo, String serverAddress) {}

    public static BackendServer getInstance(long serverNo) {
        return serverInstanceMap.get(serverNo);
    }

    public static BackendServer getRandomInstance() {
        Random random = new Random();
        int serverNo = random.nextInt(SERVER_COUNT) + 1;
        return serverInstanceMap.get(serverNo);
    }
}
