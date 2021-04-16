package com.wx.spring.aop;

import com.wx.spring.ioc.ServiceA;
import com.wx.spring.ioc.ServiceB;

/**
 * @author wangxing
 * @date 2021/4/14 13:41
 */
public class ConstructorTest {
    public static void main(String[] args) {
        // 构造注入出现无限套娃现象
//        new ServiceA(new ServiceB(new ServiceA(new ServiceB())));
    }
}
