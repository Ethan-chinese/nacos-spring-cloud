package com.sj.user.feign.service;

import com.sj.user.feign.entity.UserBean;
import com.sj.user.feign.fallback.UserFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author sijia
 * @desc ...
 * @date 2021-01-09 15:26:46
 */
@FeignClient(value = "nacos-user-server", fallbackFactory = UserFallback.class)
public interface IUserService {

    @PostMapping(value = "/getUser")
        int getUser(@RequestBody UserBean userBean);
}
