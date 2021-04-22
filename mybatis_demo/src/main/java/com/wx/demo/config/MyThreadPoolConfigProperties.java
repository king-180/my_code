package com.wx.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author wangxing
 * @date 2021/4/22 16:52
 */
@Data
@Component
@ConfigurationProperties(prefix = "gulimall.thread")
public class MyThreadPoolConfigProperties {
    private Integer coreSize;
    private Integer maxSIze;
    private Integer keepAliveTime;

}

