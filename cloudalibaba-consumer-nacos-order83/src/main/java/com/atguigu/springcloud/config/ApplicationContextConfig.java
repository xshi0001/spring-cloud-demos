package com.atguigu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
public class ApplicationContextConfig {
    @Bean
    @LoadBalanced // 使用restemplate+rbbion,一定要加这个负责均衡策略
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
