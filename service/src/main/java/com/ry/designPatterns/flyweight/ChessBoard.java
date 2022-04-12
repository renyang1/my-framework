package com.ry.designPatterns.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * 棋局
 * @author ryang
 * @Description
 * @date 2022年04月11日 7:15 下午
 */
public class ChessBoard {
    private Map<Integer, ChessPiece> chessPieceMap = new HashMap<>();

    public ChessBoard() {
        init();
    }

    /**
     *初始化棋子位置
     * @author ryang
     * @date 2022/4/11 7:22 下午
     */
    private void init() {
        chessPieceMap.put(1, new ChessPiece(1, "车", Color.RED, 0, 0));
        chessPieceMap.put(2, new ChessPiece(2, "马", Color.RED, 0, 1));
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
        System.out.println("ChessBoard#move()...");
        ChessPiece chessPiece = chessPieceMap.get(ChessPieceId);
        chessPiece.setPositionX(positionX);
        chessPiece.setPositionX(positionY);
    }
}
