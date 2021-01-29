package com.sj.admin.backstage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

/**
 * @author sijia
 * @desc ...
 * @date 2021-01-09 15:08:00
 */
@SpringBootApplication
@EnableFeignClients({"com.sj.user.feign.service",
        "com.sj.order.feign.service",
        "com.sj.product.feign.service"})
@EnableDiscoveryClient
@ComponentScans({
        @ComponentScan("com.sj.user.feign.service"),
        @ComponentScan("com.sj.order.feign.service"),
        @ComponentScan("com.sj.product.feign.service")
})
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
}
