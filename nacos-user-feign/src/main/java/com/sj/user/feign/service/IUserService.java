package com.sj.user.feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author sijia
 * @desc ...
 * @date 2021-01-09 15:26:46
 */
@FeignClient(value = "nacos-user-server")
public interface IUserService {

    @GetMapping(value = "/getUser/{userId}")
    String getUser(@PathVariable(value = "userId") String userId);
}
