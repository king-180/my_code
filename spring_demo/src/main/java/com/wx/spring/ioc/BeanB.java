package com.wx.spring.ioc;

/**
 * @author wangxing
 * @date 2021/4/14 13:49
 */
public class BeanB {
    private BeanA beanA;

    public BeanB() {
        System.out.println("------> BeanB εε»Ίζε...");
    }

    public BeanA getBeanA() {
        return beanA;
    }

    public void setBeanA(BeanA beanA) {
        this.beanA = beanA;
    }
}
