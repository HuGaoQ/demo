package com.ncamc.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * 事务切面 统一管理事务
 */
@Aspect
@Component
public class TransactionAspect {

    // 在业务方法执行之前
    @Before("execution(* com.ncamc.service.TransactionService.*(..))")
    public void beforeTransaction() {
        System.out.println("before transaction start...");
    }

    // 在业务方法执行之后
    @After("execution(* com.ncamc.service.TransactionService.*(..))")
    public void afterTransaction() {
        System.out.println("after transaction ends...");
    }

}