package com.ry.controller;

import com.ry.service.UserService;
import com.ry.entity.Users;
import com.ry.res.UserRes;
import com.ry.entity.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author renyang
 */
@RequestMapping("/users")
@RestController
public class UsersController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public Response<String> create(@RequestBody UserRes.Create res) {
        return Response.of(userService.create(res).toString());
    }

    @GetMapping("/queryUser")
    public Response<Users> getUser(String userId) {
        return Response.of(userService.queryUser(userId));
    }

}
