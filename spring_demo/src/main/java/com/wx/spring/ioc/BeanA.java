package com.wx.spring.ioc;

/**
 * @author wangxing
 * @date 2021/4/14 13:49
 */
public class BeanA {

    private BeanB beanB;

    public BeanA() {
        System.out.println("------> BeanA εε»Ίζε...");
    }

    public BeanB getBeanB() {
        return beanB;
    }

    public void setBeanB(BeanB beanB) {
        this.beanB = beanB;
    }
}
