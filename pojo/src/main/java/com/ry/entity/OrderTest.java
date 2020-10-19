package com.ry.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Description: 请描述你的文件
 *
 * @author renyang
 * @date 2020-10-15
 * <p>
 * All rights Reserved, Designed www.xiao100.com
 */
@Data
@TableName("order_test")
public class OrderTest {

    @TableId(type = IdType.AUTO)
    private int id;

    private int userId;

    private int productId;

    private int count;
}
