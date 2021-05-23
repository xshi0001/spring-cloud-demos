package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 启动类
 *
 * @author JClearLove
 * @date 2021/05/23 20:40
 */
@SpringBootApplication
@EnableEurekaClient
public class ConfigCenterMain3355 {

    public static void main(String[] args) {
        SpringApplication.run(ConfigCenterMain3355.class,args);
    }


}

