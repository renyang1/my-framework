package com.ry.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ry.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author renyang
 */
@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {

    Orders getOrderById(String id);
}