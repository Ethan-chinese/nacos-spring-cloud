package com.sj.product.feign.service;

import com.sj.product.feign.entity.ProductBean;
import com.sj.product.feign.fallback.ProductFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author sijia
 * @desc ...
 * @date 2021-01-09 15:26:46
 */
@FeignClient(value = "nacos-product-server", fallbackFactory = ProductFallback.class)
public interface IProductService {

    @PostMapping(value = "/getProduct")
        int getProduct(@RequestBody ProductBean productBean);
}
