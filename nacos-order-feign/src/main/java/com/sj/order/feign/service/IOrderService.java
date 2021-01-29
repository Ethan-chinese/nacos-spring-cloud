package com.sj.order.feign.service;

import com.sj.order.feign.entity.OrderBean;
import com.sj.order.feign.fallback.OrderFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author sijia
 * @desc ...
 * @date 2021-01-09 15:26:46
 */
@FeignClient(value = "nacos-order-server", fallbackFactory = OrderFallback.class)
public interface IOrderService {

    @PostMapping(value = "/getOrder")
        int getOrder(@RequestBody OrderBean orderBean);
}
