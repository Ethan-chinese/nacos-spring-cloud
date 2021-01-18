package com.sj.admin.backstage.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.sj.user.feign.entity.UserBean;
import com.sj.user.feign.service.IUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sijia
 * @desc ...
 * @date 2021-01-09 15:08:51
 */
@RestController
@RequestMapping(value = "admin")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @SentinelResource(value = "adminApi", blockHandler = "userBlockHandler")
    @PostMapping(value = "/getUser")
    public int getUser(@RequestBody UserBean user) {
        return userService.getUser(user);
    }

    public String userBlockHandler(BlockException e) {
        e.printStackTrace();
        return "请求用户过多";
    }
}
