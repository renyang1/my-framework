package com.ry.designpatterns.flyweight;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 棋子
 * @author ryang
 * @Description
 * @date 2022年04月11日 7:12 下午
 */
@Data
@AllArgsConstructor
public class ChessPiece1 {
    private ChessPieceUnit chessPieceUnit;
    private int positionX;
    private int positionY;
}
