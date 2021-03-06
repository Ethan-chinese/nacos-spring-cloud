package com.sj.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author sijia
 * @desc ...
 * @date 2021-01-06 15:19:25
 */
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com/sj/user/mapper")
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
