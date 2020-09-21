package com.ry.enums;

/**
 * Description: 请描述你的文件
 *
 * @author renyang
 * @date 2020-09-16
 * <p>
 * All rights Reserved, Designed www.xiao100.com
 */
public enum SexEnum {

    MALE(0, "男"),
    FAMALE(1, "女"),
    UNKONWN(2, "未知"),
    ;

    private Integer value;

    private String desc;

    SexEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
