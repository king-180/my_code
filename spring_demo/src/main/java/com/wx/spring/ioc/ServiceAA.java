package com.wx.spring.ioc;

import org.springframework.stereotype.Component;

/**
 * @author wangxing
 * @date 2021/4/14 13:30
 */
@Component
public class ServiceAA {

    private ServiceBB serviceBB;

    public void setServiceBB(ServiceBB serviceBB) {
        this.serviceBB = serviceBB;
        System.out.println("调用 ServiceAA 里面的set方法 设置了 serviceBB");
    }

}
