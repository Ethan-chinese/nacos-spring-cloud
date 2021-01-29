package com.sj.order.impl;

import com.sj.order.feign.entity.OrderBean;
import com.sj.order.feign.service.IOrderService;
import com.sj.order.mapper.OrderMapper;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author sijia
 * @desc ...
 * @date 2021-01-09 15:40:37
 */
@RestController
public class OrderServiceImpl implements IOrderService {

    @Resource
    private OrderMapper orderMapper;

    @Override
    public int getOrder(OrderBean orderBean) {
        return orderMapper.insert(orderBean);
    }
}
