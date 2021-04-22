package com.atguigu.springcloud.service;


import com.atguigu.springcloud.entity.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {
    /**
     * 创建
     *
     * @param payment
     * @return
     */
    int create(Payment payment);

    /**
     * 获取
     *
     * @param id
     * @return
     */
    Payment getPaymentById(@Param("id") Long id);
}

