package com.atme.springcloud.service;


import com.atme.springcloud.domain.Order;

/**
 * @author wsk
 * @date 2020/3/25 20:58
 */
public interface OrderService {
    void create(Order order);
}
