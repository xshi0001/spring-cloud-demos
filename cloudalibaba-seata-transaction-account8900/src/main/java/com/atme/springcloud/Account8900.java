package com.atme.springcloud;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.xxx.xxx.mapper"})
public class Account8900 {

    public static void main(String[] args) {
        // 运行spring应用
        SpringApplication.run(Account8900.class, args);
    }
}
