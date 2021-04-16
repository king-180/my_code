package com.wx.spring.ioc;

import org.springframework.stereotype.Component;

/**
 * @author wangxing
 * @date 2021/4/14 13:30
 */
@Component
public class ServiceBB {

    private ServiceAA serviceAA;

    public void setServiceAA(ServiceAA serviceAA) {
        this.serviceAA = serviceAA;
        System.out.println("调用 ServiceBB 里面的set方法 设置了 serviceAA");
    }
}
