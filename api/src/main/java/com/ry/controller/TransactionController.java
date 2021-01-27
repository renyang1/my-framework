package com.ry.controller;

import com.ry.service.UserService;
import com.ry.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 事务测试接口
 *
 * @author renyang
 */
@RestController
@RequestMapping("/tr")
public class TransactionController {

    @Autowired
    private UserService userService;

    @GetMapping("/afterCommit")
    public Response afterCommit(Integer userId) {
        userService.deleteUserById(userId);
        return Response.success();
    }


}
