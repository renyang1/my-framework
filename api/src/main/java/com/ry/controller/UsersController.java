package com.ry.controller;

import com.ry.service.UserService;
import com.ry.entity.Users;
import com.ry.res.UserRes;
import com.ry.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

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

    /**
     * 请求形式：http://127.0.0.1:8080/users/queryUser/1
     *
     * @param userId
     * @return
     */
    @GetMapping("/queryUser/{userId}")
    public Response<Users> getUser1(@PathVariable("userId") String userId) {
        return Response.of(userService.queryUser(userId));
    }

    @GetMapping("/queryUser")
    public Response<Users> getUser(@NotNull(message = "用户编号不能为空") String userId) {
        return Response.of(userService.queryUser(userId));
    }

    @PostMapping("/delete")
    public Response deleteUser(@RequestParam Integer userId) {
        userService.deleteUserById(userId);
        return Response.success();
    }

}
