package com.atme.springcloud;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author wsk
 * @date 2020/3/25 20:40
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication//取消数据源的自带创建
public class Order8901 {
    public static void main(String[] args) {
        SpringApplication.run(Order8901.class,args);
    }
}