package com.sj.product.impl;

import com.sj.product.feign.entity.ProductBean;
import com.sj.product.feign.service.IProductService;
import com.sj.product.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author sijia
 * @desc ...
 * @date 2021-01-09 15:40:37
 */
@RestController
public class ProductServiceImpl implements IProductService {

    @Resource
    private ProductMapper productMapper;

    @Override
    public int getProduct(ProductBean productBean) {
        return productMapper.insert(productBean);
    }
}
