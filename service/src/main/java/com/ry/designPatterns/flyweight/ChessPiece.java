package com.ry.designPatterns.flyweight;

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
public class ChessPiece {
    private int id;
    private String text;
    private Color color;
    private int positionX;
    private int positionY;
}
