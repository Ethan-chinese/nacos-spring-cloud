package com.sj.product.feign.fallback;

import com.sj.product.feign.entity.ProductBean;
import com.sj.product.feign.service.IProductService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * @author sijia
 * @desc ...
 * @date 2021-01-15 09:02:20
 */
@Slf4j
public class ProductFallback implements FallbackFactory<IProductService> {

    @Override
    public IProductService create(Throwable cause) {
        return new IProductService() {
            @Override
            public int getProduct(ProductBean productBean) {
                log.error("{}", cause.getMessage());
                return 0;
            }
        };
    }
}
