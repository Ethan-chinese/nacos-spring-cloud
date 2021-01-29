package com.sj.admin.backstage.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.sj.order.feign.entity.OrderBean;
import com.sj.order.feign.service.IOrderService;
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
@RequestMapping(value = "order")
@Slf4j
public class OrderController {

    private final IOrderService orderService;

    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }

    @SentinelResource(value = "adminApi", blockHandler = "orderBlockHandler")
    @PostMapping(value = "/getOrder")
    public int getOrder(@RequestBody OrderBean orderBean) {
        return orderService.getOrder(orderBean);
    }

    public String orderBlockHandler(BlockException e) {
        log.error("{}", e.getMessage());
        return "请求订单过多";
    }
}
