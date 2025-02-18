package com.ncamc.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 方法切面 记录方法日志、性能
 */
@Aspect
@Component
public class MethodExecutionTimeAspect {

    @Around("execution(* com.ncamc.service.myMethodService.*(..))") // 匹配com.ncamc.service包下的所有方法
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        // 记录方法开始时间
        long startTime = System.currentTimeMillis();
        // 执行目标方法
        Object proceed = joinPoint.proceed();
        // 记录方法结束时间
        long endTime = System.currentTimeMillis();
        // 计算执行时间
        long executionTime = endTime - startTime;
        // 打印日志
        System.out.println(joinPoint.getSignature() + " execution in " + executionTime + "ms");
        // 返回目标方法执行的结果
        return proceed;
    }

}
