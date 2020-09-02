package com.ry.mapper;

import com.ry.Orders;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrdersMapper {

    Orders getOrderById(String id);
}