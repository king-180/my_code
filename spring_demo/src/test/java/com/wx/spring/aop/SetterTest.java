package com.wx.spring.aop;

import com.wx.spring.ioc.ServiceAA;
import com.wx.spring.ioc.ServiceBB;

/**
 * @author wangxing
 * @date 2021/4/14 13:37
 */
public class SetterTest {
    public static void main(String[] args) {
        ServiceAA serviceAA = new ServiceAA();
        ServiceBB serviceBB = new ServiceBB();
        serviceAA.setServiceBB(serviceBB);
        serviceBB.setServiceAA(serviceAA);
    }
}
