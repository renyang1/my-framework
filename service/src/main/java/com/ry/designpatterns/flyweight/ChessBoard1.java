package com.ry.designpatterns.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * 棋局
 * @author ryang
 * @Description
 * @date 2022年04月11日 7:15 下午
 */
public class ChessBoard1 {
    private Map<Integer, ChessPiece1> chessPieceMap = new HashMap<>();

    public ChessBoard1() {
        init();
    }

    /**
     *初始化棋子位置
     * @author ryang
     * @date 2022/4/11 7:22 下午
     */
    private void init() {
        chessPieceMap.put(1, new ChessPiece1(ChessPieceUnitFactory.getChessPieceUnit(1), 0, 0));
        chessPieceMap.put(2, new ChessPiece1(ChessPieceUnitFactory.getChessPieceUnit(2), 0, 1));
        // ...其它棋子初始位置
    }

    /**
     * 移动棋子
     * @author ryang
     * @date 2022/4/11 7:21 下午
     * @param ChessPieceId
     * @param positionX
     * @param positionY
     */
    public void move(int ChessPieceId, int positionX, int positionY) {
        ChessPiece1 chessPiece1 = chessPieceMap.get(ChessPieceId);
        chessPiece1.setPositionX(positionX);
        chessPiece1.setPositionX(positionY);
    }
}
