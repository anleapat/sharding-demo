package com.shardingdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.shardingdemo.domain.order.mapper")
public class ShardingSpringApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShardingSpringApplication.class);
    }
}
