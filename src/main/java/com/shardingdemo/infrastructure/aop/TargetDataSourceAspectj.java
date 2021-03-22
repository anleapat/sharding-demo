package com.shardingdemo.infrastructure.aop;

import com.shardingdemo.infrastructure.util.DataSourceMsContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Slf4j
@Aspect
@Order(-1)
@Service
public class TargetDataSourceAspectj {
    @Before("@annotation(ds)")
    public void before(JoinPoint joinPoint, TargetDataSource ds) {
        DataSourceMsContextHolder.setDataSourceType("slave");
    }

    @After("@annotation(ds)")
    public void after(JoinPoint joinPoint, TargetDataSource ds) {
        DataSourceMsContextHolder.clearDataSourceType();
    }
}
