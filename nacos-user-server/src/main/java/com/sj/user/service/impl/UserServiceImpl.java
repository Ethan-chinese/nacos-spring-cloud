package com.sj.user.service.impl;

import com.sj.user.feign.service.IUserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sijia
 * @desc ...
 * @date 2021-01-09 15:40:37
 */
@RefreshScope
@RestController
public class UserServiceImpl implements IUserService {

    @Value("${user.name}")
    String name;

    @Value("${user.age}")
    String age;

    @Override
    public String getUser(String userId) {
        System.out.println(userId);
        return name + age;
    }
}
