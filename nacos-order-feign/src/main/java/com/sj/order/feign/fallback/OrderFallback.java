package com.sj.order.feign.fallback;

import com.sj.order.feign.entity.OrderBean;
import com.sj.order.feign.service.IOrderService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * @author sijia
 * @desc ...
 * @date 2021-01-15 09:02:20
 */
@Slf4j
public class OrderFallback implements FallbackFactory<IOrderService> {

    @Override
    public IOrderService create(Throwable cause) {
        return new IOrderService() {
            @Override
            public int getOrder(OrderBean orderBean) {
                log.error("{}", cause.getMessage());
                return 0;
            }
        };
    }
}
