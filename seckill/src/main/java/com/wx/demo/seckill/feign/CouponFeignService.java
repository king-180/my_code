package com.wx.demo.seckill.feign;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author wangxing
 * @date 2021/7/19 0:39
 */
@FeignClient("gulimall-coupon")
public interface CouponFeignService {


}
