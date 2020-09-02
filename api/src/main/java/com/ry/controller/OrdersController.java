package com.ry.controller;

import com.ry.OrderService;
import com.ry.Orders;
import com.ry.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/queryOrder")
    public Response<Orders> getPaidOrderInfo(String orderId) {
        return Response.of(orderService.queryOrder(orderId));
    }

    @GetMapping("/hello")
    public Response<String> getPaidOrderInfo() {
        return Response.of("hello");
    }

}
