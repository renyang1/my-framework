package com.ry.designPatterns.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * 享元工厂类，缓存享元类对象
 * @author ryang
 * @Description
 * @date 2022年04月11日 7:12 下午
 */
public class ChessPieceUnitFactory {

    private static final Map<Integer, ChessPieceUnit> chessPieceUnitMap = new HashMap<>();

    static {
        chessPieceUnitMap.put(1, new ChessPieceUnit(1, "车", Color.RED));
        chessPieceUnitMap.put(2, new ChessPieceUnit(2, "马", Color.RED));
    }

    public static ChessPieceUnit getChessPieceUnit(int chessPieceId) {
        return chessPieceUnitMap.get(chessPieceId);
    }
}
