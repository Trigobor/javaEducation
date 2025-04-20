package com.springapp.first.javamodule11.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogExecutionAspect {

    @Around("@annotation(com.springapp.first.javamodule11.aspect.LogExecutionTime)")
    public Object logTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long duration = System.currentTimeMillis() - start;
        System.out.println("Метод " + joinPoint.getSignature() + " выполнился за " + duration + " мс");

        return result;
    }
}
