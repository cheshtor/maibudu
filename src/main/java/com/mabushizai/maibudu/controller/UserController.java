package com.mabushizai.maibudu.controller;

import com.mabushizai.maibudu.config.ApiResponse;
import com.mabushizai.maibudu.domain.User;
import com.mabushizai.maibudu.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping(value = "/shareCode")
    public ApiResponse<String> getShareCode() {
        User user = userService.findByUid();
        if (null == user) {
            return ApiResponse.ok("");
        } else {
            return ApiResponse.ok(user.getCode());
        }
    }

}
