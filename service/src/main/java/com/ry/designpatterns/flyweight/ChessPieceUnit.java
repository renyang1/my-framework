package com.ry.designpatterns.flyweight;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 享元类
 * @author ryang
 * @Description
 * @date 2022年04月11日 7:12 下午
 */
@Getter
@AllArgsConstructor
public class ChessPieceUnit {
    private int id;
    private String text;
    private Color color;
}
