package com.atme.springcloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 启动类
 */
@SpringBootApplication
@EnableDiscoveryClient //  原生注解 @EnableDiscoveryClient 开启服务注册发现功能
public class Account8900 {

    public static void main(String[] args) {
        // 运行spring应用
        SpringApplication.run(Account8900.class, args);
    }
}
