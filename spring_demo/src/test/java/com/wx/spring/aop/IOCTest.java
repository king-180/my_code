package com.wx.spring.aop;

import com.wx.spring.ioc.BeanA;
import com.wx.spring.ioc.BeanB;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author wangxing
 * @date 2021/4/14 13:53
 */
public class IOCTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        BeanA beanA = context.getBean("beanA", BeanA.class);
        BeanB beanB = context.getBean("beanB", BeanB.class);
    }
}
