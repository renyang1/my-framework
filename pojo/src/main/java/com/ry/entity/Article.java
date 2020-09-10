package com.ry.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 文章信息表(Article)实体类
 *
 * @author renyang
 * @since 2020-09-09 20:52:02
 */
@Data
@TableName("article")
public class Article {

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 标题
     */
    private String title;
    /**
     * 副标题
     */
    private String subtitle;
    /**
     * 列表图片链接地址
     */
    private String url;
    /**
     * 阅读量
     */
    private Long clickCount;
    /**
     * 是否删除  0未删除 1删除
     */
    private Integer isDelete;

}