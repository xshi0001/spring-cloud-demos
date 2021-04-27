package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * controller类
 *
 * @author JClearLove
 * @date 2021/04/25 21:20
 */
@RestController
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;

    @RequestMapping("/payment/consul")
    public String paymentZk() {
        return "springcloud with consul" + serverPort + UUID.randomUUID().toString();
    }

}

