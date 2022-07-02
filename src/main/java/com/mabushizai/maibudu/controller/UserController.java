package com.mabushizai.maibudu.controller;

import com.mabushizai.maibudu.config.ApiResponse;
import com.mabushizai.maibudu.domain.User;
import com.mabushizai.maibudu.dto.UserRegisterRequest;
import com.mabushizai.maibudu.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Pengyu Gan
 * CreateDate 2022/6/29
 */
@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping(value = "/register")
    public ApiResponse<User> register(@RequestBody UserRegisterRequest request) {
        User user = userService.addUser(request);
        return ApiResponse.ok(user);
    }

    @GetMapping(value = "/get")
    public ApiResponse<User> get() {
        User user = userService.findByUid();
        return ApiResponse.ok(user);
    }

}
