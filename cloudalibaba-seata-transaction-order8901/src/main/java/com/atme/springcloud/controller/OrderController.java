package com.atme.springcloud.controller;


import com.atme.springcloud.domain.CommonResult;
import com.atme.springcloud.domain.Order;
import com.atme.springcloud.service.OrderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wsk
 * @date 2020/3/25 21:24
 */
@RestController
public class OrderController {

    @Resource
    private OrderService orderService;

    @RequestMapping("/order/create/")
    public CommonResult create(Order order) {
        orderService.create(order);
        return new CommonResult(200, "订单创建成功");
    }
}
