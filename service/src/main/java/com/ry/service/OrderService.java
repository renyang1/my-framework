package com.ry.service;

import com.ry.mapper.OrdersMapper;
import com.ry.entity.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description: 请描述你的文件
 *
 * @author renyang
 * @date 2020-09-01
 * <p>
 * All rights Reserved, Designed www.xiao100.com
 */
@Service
public class OrderService {

    @Autowired
    private OrdersMapper ordersMapper;

    public Orders queryOrder(String orderId) {
        return ordersMapper.getOrderById(orderId);
    }
}
