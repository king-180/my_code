package com.wx.demo.util;

import org.springframework.context.ConfigurableApplicationContext;

import java.util.Map;

/**
 * @author liuyulong
 * @description 获取spring的相关对象
 * @date 2021-03-22
 **/
public class SpringBeanUtil {
    /**
     * 将管理上下文的applicationContext设置成静态变量，供全局调用
     */
    private static ConfigurableApplicationContext applicationContext;

    public static void setApplicationContext(ConfigurableApplicationContext applicationContext) {
        if (SpringBeanUtil.applicationContext == null) {
            SpringBeanUtil.applicationContext = applicationContext;
        }
    }

    //定义一个获取已经实例化bean的方法
    public static <T> T getBean(Class<T> c) {
        return applicationContext.getBean(c);
    }

    //定义一个获取已经实例化bean的方法
    public static <T> T getBean(String name, Class<T> c) {
        return applicationContext.getBean(name, c);
    }

    /**
     * 一组已经实例化bean的方法
     *
     * @param c
     * @param <T>
     * @return
     */
    public static <T> Map<String, T> getBeansOfType(Class<T> c) {
        return applicationContext.getBeansOfType(c);
    }
}
