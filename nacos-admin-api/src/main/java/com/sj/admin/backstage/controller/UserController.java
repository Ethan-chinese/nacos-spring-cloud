package com.sj.admin.backstage.controller;

import com.sj.user.feign.service.IUserService;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping(value = "/getUser")
    public String getUser() {
        return userService.getUser("123312123123132123");
    }
}
