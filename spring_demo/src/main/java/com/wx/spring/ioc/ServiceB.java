package com.wx.spring.ioc;

import org.springframework.stereotype.Component;

/**
 * @author wangxing
 * @date 2021/4/14 13:30
 */
@Component
public class ServiceB {

    private ServiceA serviceA;

    public ServiceB(ServiceA serviceA) {
        this.serviceA = serviceA;
    }
}
