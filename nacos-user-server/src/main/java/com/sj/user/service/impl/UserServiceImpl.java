package com.sj.user.service.impl;

import com.sj.user.feign.entity.UserBean;
import com.sj.user.feign.service.IUserService;
import com.sj.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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

    @Resource
    private UserMapper userMapper;

    @Override
    public int getUser(UserBean userBean) {
        return userMapper.insert(userBean);
    }
}
