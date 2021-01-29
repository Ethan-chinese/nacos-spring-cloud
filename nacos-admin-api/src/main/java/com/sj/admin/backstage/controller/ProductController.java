package com.sj.admin.backstage.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.sj.product.feign.entity.ProductBean;
import com.sj.product.feign.service.IProductService;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping(value = "product")
@Slf4j
public class ProductController {

    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @SentinelResource(value = "adminApi", blockHandler = "productBlockHandler")
    @PostMapping(value = "/getProduct")
    public int getProduct(@RequestBody ProductBean productBean) {
        return productService.getProduct(productBean);
    }

    public String productBlockHandler(BlockException e) {
        log.error("{}", e.getMessage());
        return "请求商品过多";
    }
}
