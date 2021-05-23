package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 主启动类
 *
 * @author xshi0
 */
@SpringBootApplication
@EnableEurekaClient
public class StreamMqMain8801 {
    public static void main(String[] args) {
        SpringApplication.run(StreamMqMain8801.class, args);
    }
}
