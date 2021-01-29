package com.sj.product;

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
@MapperScan("com/sj/product/mapper")
public class ProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }
}
