package com.wx.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author wangxing
 * @date 2021/4/14 13:09
 */
@Aspect
@Component
public class MyAspect {

    @Before("execution(public int com.wx.spring.aop.CalcServiceImpl.*(..))")
    public void beforeNotify() {
        System.out.println("--------> @Before 前置通知... ");
    }

    @After("execution(public int com.wx.spring.aop.CalcServiceImpl.*(..))")
    public void afterNotify() {
        System.out.println("--------> @After 后置通知... ");
    }

    @AfterReturning("execution(public int com.wx.spring.aop.CalcServiceImpl.*(..))")
    public void afterReturningNotify() {
        System.out.println("--------> @AfterReturning 返回后通知... ");
    }

    @AfterThrowing("execution(public int com.wx.spring.aop.CalcServiceImpl.*(..))")
    public void afterThrowingNotify() {
        System.out.println("--------> @AfterThrowing 异常通知... ");
    }

    @Around("execution(public int com.wx.spring.aop.CalcServiceImpl.*(..))")
    public Object aroundNotify(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object retVal = null;
        System.out.println("--------> @Around 环绕通知之前... ");
        retVal = proceedingJoinPoint.proceed();
        System.out.println("--------> @Around 环绕通知之后... ");
        return retVal;
    }

}

