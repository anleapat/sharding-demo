package com.shardingdemo.infrastructure.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Slf4j
@Aspect
@Service
public class ServiceMethodLogAspectj {
    @Pointcut("@annotation(com.shardingdemo.infrastructure.aop.ServiceMethodLog)")
    private void methodLog() {
    }

    @Around("methodLog()")
    public Object after(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object result = proceedingJoinPoint.proceed();
        String clazz = proceedingJoinPoint.getTarget().getClass().getName();
        String method = proceedingJoinPoint.getSignature().getName();
        doLog(clazz, method, "after", result);
        return result;
    }

    @Before("methodLog()")
    public void before(JoinPoint joinPoint) {
        String clazz = joinPoint.getTarget().getClass().getName();
        String method = joinPoint.getSignature().getName();
        doLog(clazz, method, "before", joinPoint.getArgs());
    }

    @After("methodLog()")
    public void doAround(JoinPoint joinPoint) {
        String clazz = joinPoint.getTarget().getClass().getName();
        String method = joinPoint.getSignature().getName();
        doLog(clazz, method, "doAround", joinPoint.getArgs());
    }

    private void doLog(String clazz, String method, String type, Object params) {
        log.info("{}.{} {} Execute: param:{}", clazz, method, type, params);
    }
}
