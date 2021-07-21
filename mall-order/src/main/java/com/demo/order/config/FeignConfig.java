package com.demo.order.config;

import feign.RequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wangxing
 * @date 2021/7/21 15:28
 */
@Slf4j
@Configuration
public class FeignConfig {

    @Bean("requestInterceptor")
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            log.info("FeignConfig.requestInterceptor().apply()...");
            // 1.拿到刚进来的请求
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            // 一开始的请求
            HttpServletRequest request;
            if (requestAttributes != null) {
                request = requestAttributes.getRequest();
                String cookie = request.getHeader("Cookie");
                // 2.同步请求头数据，cookie
                // 3. 将原先请求的 Cookie 设置到新请求的请求头
                requestTemplate.header("Cookie", cookie);
            }
        };
    }

}
